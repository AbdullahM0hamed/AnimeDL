package com.anime.dl.reducers

import com.anime.dl.actions.DisplayExtensions
import com.anime.dl.App
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.ExtensionListState
import org.rekotlin.Action

fun extensionListReducer(action: Action, extensionListState: ExtensionListState?): ExtensionListState {
    var state = extensionListState ?: ExtensionListState()
    when (action) {
        is DisplayExtensions -> {
            val manager = ExtensionManager(App.appContext)
            state = state.copy(
                installedExtensions = manager.getInstalledExtensions(),
                availableExtensions = manager.getAvailableExtensions()
            )
        }
    }

    return state
}
