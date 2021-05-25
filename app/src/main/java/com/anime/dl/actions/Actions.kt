package com.anime.dl.actions

import android.app.Activity
import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.Source
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage

// Extension Actions
class FindExtensions
data class InstallExtension(val extension: Extension)
data class GetBrowseAnime(val source: Source, val page: Int, val activity: Activity)
data class BrowseAnimeResult(val page: AnimePage)
data class UpdateAnimeInfo(val anime: AnimeInfo, val source: Source)
