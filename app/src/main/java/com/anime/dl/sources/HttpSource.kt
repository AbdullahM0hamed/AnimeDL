package com.anime.dl.sources

import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo
import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import rx.Observable
import rx.Producer
import rx.Subscriber
import rx.Subscription
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.atomic.AtomicBoolean

abstract class HttpSource : Source {

    private val DEFAULT_CACHE_CONTROL = CacheControl.Builder().maxAge(10, MINUTES).build()
    private val DEFAULT_BODY: RequestBody = FormBody.Builder().build()

    fun GET(
        url: String,
        headers: Headers = headersBuilder().build(),
        cache: CacheControl = DEFAULT_CACHE_CONTROL
    ): Request {
        return Request.Builder()
            .url(url)
            .headers(headers)
            .cacheControl(cache)
            .build()
    }

    fun POST(
        url: String,
        headers: Headers = headersBuilder().build(),
        body: RequestBody = DEFAULT_BODY,
        cache: CacheControl = DEFAULT_CACHE_CONTROL
    ): Request {
        return Request.Builder()
            .url(url)
            .post(body)
            .headers(headers)
            .cacheControl(cache)
            .build()
    }

    abstract val baseUrl: String

    open val versionId = 1

    val headers: Headers by lazy { headersBuilder().build() }

    open val client = OkHttpClient.Builder().build()

    protected open fun headersBuilder() = Headers.Builder().apply {
        add("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64)")
    }

    abstract fun browseAnimeRequest(page: Int): Request

    open fun browseAnimeSelector(): String? = null

    open fun browseAnimeFromElement(element: Element): AnimeInfo? = null

    open fun browseAnimeNextPageSelector(): String? = null

    open fun browseAnimeFromJson(json: String): List<AnimeInfo>? = null

    open fun browseAnimeNextPageFromJson(json: String): Boolean = false

    open fun episodeListRequest(link: String, page: Int): Request = browseAnimeRequest(page)

    open fun episodeListSelector(): String? = null

    open fun episodeListNextPageSelector(): String? = null

    open fun episodeFromElement(element: Element): EpisodeInfo? = null

    open fun episodeListFromJson(link: String, json: String): List<EpisodeInfo> = emptyList()

    open fun episodeListNextPageFromJson(json: String): Boolean = false

    override fun getAnimeList(page: Int): AnimePage = runBlocking { fetchAnimeList(page).awaitSingle() }

    override fun getEpisodeList(anime: AnimeInfo, page: Int): List<EpisodeInfo> = runBlocking { fetchEpisodeList(anime, page).awaitSingle() }

    fun fetchAnimeList(page: Int): Observable<AnimePage> {
        return client.newCall(browseAnimeRequest(page))
            .asObservableSuccess()
            .map { response ->
                animeListParse(response)
            }
    }

    fun animeListParse(response: Response): AnimePage {
        var anime: List<AnimeInfo>? = null
        var hasNextPage: Boolean = false

        if (browseAnimeSelector() != null) {
            val document = Jsoup.parse(response!!.body!!.string(), response.request.url.toString())
            anime = document.select(browseAnimeSelector()).map { element ->
                browseAnimeFromElement(element)!!
            }

            hasNextPage = document.select(browseAnimeNextPageSelector()).first() != null
        } else {
            val json = response!!.body!!.string()
            anime = browseAnimeFromJson(json)
            hasNextPage = browseAnimeNextPageFromJson(json)
        }

        return AnimePage(anime!!, hasNextPage)
    }

    fun fetchEpisodeList(anime: AnimeInfo, page: Int): Observable<List<EpisodeInfo>> {
        return client.newCall(episodeListRequest(anime.link, page))
            .asObservableSuccess()
            .map { response ->
                episodeListParse(response, page, anime)
            }
    }

    fun episodeListParse(response: Response, page: Int, anime: AnimeInfo): List<EpisodeInfo> {
        var episodes: List<EpisodeInfo> = emptyList()
        var pageCount: Int = page

        if (episodeListSelector() != null) {
            var document: Document = Jsoup.parse(response!!.body!!.string(), response.request.url.toString())
            episodes = document!!.select(episodeListSelector()).map { element ->
                episodeFromElement(element)!!
            }

            while (document!!.select(episodeListNextPageSelector()).first() != null) {
                pageCount += 1
                val response = client.newCall(episodeListRequest(anime.link, pageCount)).execute()
                document = Jsoup.parse(response!!.body!!.string(), response.request.url.toString())

                episodes += document!!.select(episodeListSelector()).map { element ->
                    episodeFromElement(element)!!
                }
            }
        } else {
            var json = response!!.body!!.string()
            episodes = episodeListFromJson(anime.link, json)

            while (episodeListNextPageFromJson(json)) {
                pageCount += 1
                val response = client.newCall(episodeListRequest(anime.link, pageCount)).execute()
                json = response!!.body!!.string()
                episodes += episodeListFromJson(anime.link, json)
            }
        }

        return episodes!!
    }

    fun Call.asObservableSuccess(): Observable<Response> {
        return asObservable().doOnNext { response ->
            if (!response.isSuccessful) {
                response.close()
                throw Exception("HTTP error ${response.code}")
            }
        }
    }

    fun Call.asObservable(): Observable<Response> {
        return Observable.unsafeCreate { subscriber ->
            val call = clone()

            val requestArbiter = object : AtomicBoolean(), Producer, Subscription {
                override fun request(n: Long) {
                    if (n == 0L || !compareAndSet(false, true)) return

                    try {
                        val response = call.execute()
                        if (!subscriber.isUnsubscribed) {
                            subscriber.onNext(response)
                            subscriber.onCompleted()
                        }
                    } catch (error: Exception) {
                        if (!subscriber.isUnsubscribed) {
                            subscriber.onError(error)
                        }
                    }
                }

                override fun unsubscribe() {
                    call.cancel()
                }

                override fun isUnsubscribed(): Boolean {
                    return call.isCanceled()
                }
            }

            subscriber.add(requestArbiter)
            subscriber.setProducer(requestArbiter)
        }
    }

    suspend fun <T> Observable<T>.awaitSingle(): T = single().awaitOne()

    private suspend fun <T> Observable<T>.awaitOne(): T = suspendCancellableCoroutine { cont ->
        cont.unsubscribeOnCancellation(
            subscribe(
                object : Subscriber<T>() {
                    override fun onStart() {
                        request(1)
                    }

                    override fun onNext(t: T) {
                        cont.resume(t)
                    }

                    override fun onCompleted() {
                        if (cont.isActive) cont.resumeWithException(
                            IllegalStateException(
                                "Should have invoked onNext"
                            )
                        )
                    }

                    override fun onError(e: Throwable) {}
                }
            )
        )
    }

    fun <T> CancellableContinuation<T>.unsubscribeOnCancellation(sub: Subscription) =
    invokeOnCancellation { sub.unsubscribe() }
}
