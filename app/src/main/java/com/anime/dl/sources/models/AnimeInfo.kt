package com.anime.dl.sources.models

data class AnimeInfo(
    val key: String,
    val title: String,
    val link: String,
    val description: String = "",
    val cover: String = "",
    val studios: String = "",
    val genres: List<String> = emptyList(),
    val status: Int = UNKNOWN
) {

    companion object {
        const val UNKNOWN = 0
        const val AIRING = 1
        const val COMPLETED = 2
        const val CANCELLED = 3
    }
}
