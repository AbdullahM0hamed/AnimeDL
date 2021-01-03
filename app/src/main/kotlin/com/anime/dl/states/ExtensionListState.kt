package com.anime.dl.states

import com.anime.dl.extensions.models.Extension
import org.rekotlin.StateType

data class ExtensionListState(
    var installedExtensions = List<Extension.Installed>? = null,
    var availableExtensions = List<Extension.Available>? = null
) : StateType
