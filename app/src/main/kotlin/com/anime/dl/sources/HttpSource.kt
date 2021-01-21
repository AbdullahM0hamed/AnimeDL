package com.anime.dl.sources

import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.EpisodeInfo
import okhttp3.Headers
import okhttp3.OkHttpClient
import java.security.MessageDigest

abstract class HttpSource : Source {

    open val versionId = 1

    override val id by lazy {
        val key = "${name.toLowerCase()}/$lang/$versionId"
        val bytes = MessageDigest.getInstance("MD5").digest(key.toByteArray())
        (0..7).map { bytes[it].toLong() and 0xff shl 8 * (7 - it) }.reduce(Long::or) and Long.MAX_VALUE
    }

    val headers: Headers by lazy { headersBuilder().build() }

    open val client = OkHttpClient.Builder().build()

    protected open fun headersBuilder() = Headers.Builder().apply {
        add("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64)")
    }
}
