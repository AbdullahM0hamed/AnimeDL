package com.anime.dl.actions

import com.anime.dl.extensions.models.Extension
import org.rekotlin.Action

class findAvailableExtensions(
    val installedExtensions: List<Extension.Installed>,
    val availableExtensions: List<Extension.Available>
) : Action
