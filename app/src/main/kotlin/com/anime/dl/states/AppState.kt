package com.anime.dl.states

import org.rekotlin.StateType

data class AppState(
    var extensionListState: ExtensionListState? = null
) : StateType
