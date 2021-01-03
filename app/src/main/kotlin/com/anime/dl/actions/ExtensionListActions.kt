package com.anime.dl.actions

import com.anime.dl.extensions.models.Extension
import org.rekotlin.Action

class InitializeExtensionList(
    val installedExtensions: List<Extension.Installed>,
    val availableExtensions: List<Extension.Available>
) : Action

class DisplayExtensions(
    val installedExtensions: List<Extension.Installed>,
    val availableExtensions: List<Extension.Available>
) : Action
