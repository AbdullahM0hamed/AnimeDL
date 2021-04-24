package com.anime.dl.states

import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.models.AnimePage

data class AppState(
    val extensionListState: ExtensionListState = ExtensionListState(),
    val browseAnimeState: BrowseAnimeState = BrowseAnimeState()
)

data class ExtensionListState(
    val installedExtensions: List<Extension.Installed> = emptyList(),
    val availableExtensions: List<Extension.Available> = emptyList()
)

data class BrowseAnimeState(
    val browseAnime: AnimePage? = null
)
