package com.anime.dl.reducers

import com.anime.dl.App
import com.anime.dl.actions.FindExtensions
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.ExtensionListState

fun extensionListReducer(state: ExtensionListState, action: Any): ExtensionListState {
  when (action) {
    is FindExtensions -> {
      val manager = ExtensionManager(App.applicationContext())
      var state = state ?: ExtensionListState()
      state = state.copy(manager.installedExtensions, manager.availableExtensions)

      return state
    }
  }

  return state
}
