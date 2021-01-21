package com.anime.dl.sources

import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

interface Source {

    val id: Long

    val name: String

    val lang: String

    suspend fun getAnimeDetails(anime: AnimeInfo): AnimeInfo

    suspend fun getEpisodeList(anime: AnimeInfo): List<EpisodeInfo>

    suspend fun getAnimeList(page: Int): AnimePage
}
