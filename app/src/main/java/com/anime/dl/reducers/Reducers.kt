package com.anime.dl.reducers

import com.anime.dl.App
import com.anime.dl.actions.FindExtensions
import com.anime.dl.actions.GetBrowseAnime
import com.anime.dl.actions.InstallExtension
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.AppState
import com.anime.dl.states.BrowseAnimeState
import com.anime.dl.states.ExtensionListState

fun appStateReducer(state: AppState, action: Any) = AppState(
    extensionListState = extensionListReducer(state.extensionListState, action),
    browseAnimeState = browseAnimeStateReducer(state.browseAnimeState, action)
)

fun extensionListReducer(state: ExtensionListState, action: Any): ExtensionListState {
    var currentState = state ?: ExtensionListState()
    val context = App.applicationContext()
    var manager = ExtensionManager(context)

    when (action) {
        is FindExtensions -> {
            currentState = currentState.copy(manager.installedExtensions, manager.availableExtensions)
        }
        is InstallExtension -> {
            manager.installExtension(action.extension)
            manager = ExtensionManager(context)
            currentState = ExtensionListState(manager.installedExtensions, manager.availableExtensions)
        }
    }

    return currentState
}

fun browseAnimeStateReducer(state: BrowseAnimeState, action: Any): BrowseAnimeState {
    if (action is GetBrowseAnime) {
        var currentState = state ?: BrowseAnimeState()
        val browseList = action.source.getAnimeList(action.page)
        currentState = currentState.copy(browseList)

        return currentState
    }

    return state
}
