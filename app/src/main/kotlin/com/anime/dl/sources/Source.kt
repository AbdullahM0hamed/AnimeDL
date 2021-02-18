package com.anime.dl.sources

import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

interface Source {

    val id: Long

    val name: String

    val lang: String

    fun getAnimeDetails(anime: AnimeInfo): AnimeInfo

    fun getEpisodeList(anime: AnimeInfo): List<EpisodeInfo>

    fun getAnimeList(page: Int): AnimePage
}
