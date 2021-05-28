package com.anime.dl.states

import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.EpisodeInfo

data class AppState(
    val extensionListState: ExtensionListState = ExtensionListState(),
    val browseAnimeState: BrowseAnimeState = BrowseAnimeState(),
    val animeInfoState: AnimeInfoState = AnimeInfoState()
)

data class ExtensionListState(
    val installedExtensions: List<Extension.Installed> = emptyList(),
    val availableExtensions: List<Extension.Available> = emptyList()
)

data class BrowseAnimeState(
    val browseAnime: AnimePage? = null,
    val query: String? = null
)

data class AnimeInfoState(
    val anime: AnimeInfo? = null,
    val episodes: List<EpisodeInfo>? = null
)
