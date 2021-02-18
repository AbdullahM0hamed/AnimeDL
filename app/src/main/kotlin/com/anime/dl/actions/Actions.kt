package com.anime.dl.actions

import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.Source

// Extension Actions
class FindExtensions
data class InstallExtension(val extension: Extension)
data class GetBrowseAnime(val source: Source, val page: Int)
