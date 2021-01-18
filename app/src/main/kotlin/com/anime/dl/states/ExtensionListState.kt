package com.anime.dl.states

import com.anime.dl.extensions.models.Extension

data class ExtensionListState(
    val installedExtensions: List<Extension.Installed> = emptyList(),
    val availableExtensions: List<Extension.Available> = emptyList())
