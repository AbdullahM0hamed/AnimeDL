package com.anime.dl.sources.models

data class AnimePage(
    val anime: List<AnimeInfo>,
    val hasNextPage: Boolean
)
