package com.anime.dl.reducers

import com.anime.dl.App
import com.anime.dl.actions.AnimeInfoResult
import com.anime.dl.actions.BrowseAnimeResult
import com.anime.dl.actions.FindExtensions
import com.anime.dl.actions.GetBrowseAnime
import com.anime.dl.actions.InstallExtension
import com.anime.dl.actions.NullifyAnimeInfoState
import com.anime.dl.actions.UpdateAnimeInfo
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.AnimeInfoState
import com.anime.dl.states.AppState
import com.anime.dl.states.BrowseAnimeState
import com.anime.dl.states.ExtensionListState
import com.anime.dl.ui.main.mainStore

fun appStateReducer(state: AppState, action: Any) = AppState(
    extensionListState = extensionListReducer(state.extensionListState, action),
    browseAnimeState = browseAnimeStateReducer(state.browseAnimeState, action),
    animeInfoState = animeInfoStateReducer(state.animeInfoState, action)
)

fun extensionListReducer(state: ExtensionListState, action: Any): ExtensionListState {
    var currentState = state
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
    var currentState = state
    if (action is GetBrowseAnime) {
        Thread(
            Runnable {
                val browseList = action.source.getAnimeList(action.page)
                action.activity.runOnUiThread(Runnable { mainStore.dispatch(BrowseAnimeResult(browseList)) })
            }
        ).start()
    } else if (action is BrowseAnimeResult) {
        currentState = currentState.copy(action.page)
    }

    return currentState
}

fun animeInfoStateReducer(state: AnimeInfoState, action: Any): AnimeInfoState {
    var currentState = state
    if (action is UpdateAnimeInfo) {
        Thread(
            Runnable {
                val anime = action.source.getAnimeDetails(action.anime!!)
                val episodes = action.source.getEpisodeList(anime, 1)
                action.activity.runOnUiThread(Runnable { mainStore.dispatch(AnimeInfoResult(anime, episodes)) })
            }
        ).start()
    } else if (action is AnimeInfoResult) {
        currentState = currentState.copy(action.anime, action.episodes)
    } else if (action is NullifyAnimeInfoState) {
        currentState = currentState.copy(null, null)
    }

    return currentState
}
