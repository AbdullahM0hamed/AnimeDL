package com.anime.dl.reducers

import com.anime.dl.actions.findAvailableExtensions
import com.anime.dl.App
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.ExtensionListState
import org.rekotlin.Action

fun extensionListReducer(action: Action, extensionListState: ExtensionListState?): ExtensionListState {
    var state = extensionListState ?: ExtensionListState()
    when (action) {
        is findAvailableExtensions -> {
            val manager = ExtensionManager(App.applicationContext())
            state = state.copy(
                installedExtensions = manager.installedExtensions,
                availablinstalledExtensions = manager.availableExtensions
            )
        }
    }

    return state
}
