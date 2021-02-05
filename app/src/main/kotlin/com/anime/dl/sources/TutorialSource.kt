package com.anime.dl.sources

import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

class TutorialSource : Source {

    override val id = 0L

    override val name = App.applicationContext().getString(R.string.tutorial_source)

    override val lang = "all"

    override suspend fun getAnimeList(page: Int): AnimePage {
        return AnimePage(getTutorialAnime(page), true)
    }

    override suspend fun getAnimeDetails(anime: AnimeInfo): AnimeInfo { return anime }

    override suspend fun getEpisodeList(anime: AnimeInfo): List<EpisodeInfo> {
        val chapter1 = EpisodeInfo(
            "1",
            "Chapter 1",
            System.currentTimeMillis()
        )

        val chapter2 = chapter1.copy("2", "Chapter 2")
        val chapter3 = chapter1.copy("3", "Chapter 3")

        return listOf(chapter1, chapter2, chapter3)
    }

    private fun getTutorialAnime(page: Int): List<AnimeInfo> {
        val list = mutableListOf<AnimeInfo>()
        val id = (page - 1) * 20 + 1
        val anime1 = AnimeInfo(
            "$id",
            "Tutorial Anime $id",
            "Tutorial Anime for the sake of the tutorial!"
        )

        list += anime1

        for (i in 1..19) {
            list += anime1.copy(key = "${id + 1}", title = "Anime ${id + 1}")
        }

        return list
    }
}
