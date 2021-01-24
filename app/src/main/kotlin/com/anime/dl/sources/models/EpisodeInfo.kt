package com.anime.dl.sources.models

data class EpisodeInfo(
    var key: String,
    var title: String,
    var dateUpload: Long = 0,
    var ep_number: Float = -1f,
    var thumbnail: String = ""
)
