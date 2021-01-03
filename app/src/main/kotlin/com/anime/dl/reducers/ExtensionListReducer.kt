package com.anime.dl.reducers

import com.anime.dl.actions.DisplayExtensions
import com.anime.dl.states.ExtensionListState
import org.rekotlin.Action

fun extensionListReducer(action: Action, extensionListState: ExtensionListState?): ExtensionListState {
    var state = extensionListState ?: ExtensionListState()
    when (action) {
        is DisplayExtensions -> {
            state = state.copy(
                installedExtensions = action.installedExtensions,
                availableExtensions = action.availableExtensions
            )
        }
    }

    return state
}
