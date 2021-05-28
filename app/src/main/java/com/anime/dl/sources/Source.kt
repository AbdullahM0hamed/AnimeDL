package com.anime.dl.sources

import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

interface Source {

    val id: Long
    
    val baseUrl: String

    val name: String

    val lang: String

    fun getAnimeDetails(anime: AnimeInfo): AnimeInfo

    fun getEpisodeList(anime: AnimeInfo, page: Int): List<EpisodeInfo>

    fun getAnimeList(page: Int): AnimePage

    fun getSearchList(query: String, page: Int): AnimePage
}
