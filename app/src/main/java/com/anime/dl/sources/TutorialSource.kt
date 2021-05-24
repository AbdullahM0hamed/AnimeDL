package com.anime.dl.sources

import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo
import org.json.JSONArray
import org.json.JSONObject

class TutorialSource : HttpSource {

    override val id = 0L

    override val name = App.applicationContext().getString(R.string.tutorial_source)

    override val lang = "all"

    override val baseUrl = "https://github.com/AbdullahM0hamed/AnimeDL"

    override fun getAnimeDetails(anime: AnimeInfo): AnimeInfo { return anime }

    override fun browseAnimeRequest(page: Int) = GET("https://raw.githubusercontent.com/AbdullahM0hamed/AnimeDL/dev/TutorialSourceData/Anime.json")

    override fun browseAnimeFromJson(json: String): List<AnimeInfo>? {
        val array = JSONArray(json)

        return array.toList().map { info ->
            val json = info as JSONObject
            AnimeInfo(
                key="0",
                title=json.getString("title"),
                link=json.getString("link"),
                description=json.getString("description"),
                cover=json.getString("img")
                genres=json.getJSONArray("genres").toList()
            )
        }
    }

    override fun episodeListRequest(link: String, page: Int) = GET("https://raw.githubusercontent.com/AbdullahM0hamed/AnimeDL/dev/TutorialSourceData/Episodes.json")

    override fun episodeListFromJson(link: String, json: String): List<EpisodeInfo>? {
        val array = JSONObject(json)
        val pattern = "anime/([0-9]+)/".toRegex()
        val match = pattern.find(anime.link)
        var (aniId) = match!!.destructured
        aniId = aniId.toString()

        return array.getJSONArray(aniId).toList().map { info ->
            val json = info as JSONObject
            EpisodeInfo(
                key=json.getString("key"),
                title=json.getString("number") + ". " + json.getString("title"),
                dateUpload=System.currentTimeMillis(),
                ep_number=-1f,
                thumbnail=if (json.toMap().containsKey("thumbnail")) {
                    json.getString("thumbnail")
                } else {
                    ""
                }
            )
        }
    }
}
