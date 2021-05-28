package com.anime.dl.actions

import android.app.Activity
import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.Source
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

// Extension Actions
class FindExtensions
data class InstallExtension(val extension: Extension)
data class BrowseAnimeResult(val page: AnimePage)
data class AnimeInfoResult(val anime: AnimeInfo, val episodes: List<EpisodeInfo>)
class ClearAnimeState
