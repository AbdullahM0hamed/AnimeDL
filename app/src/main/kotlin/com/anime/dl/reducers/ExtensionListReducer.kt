package com.anime.dl.reducers

import com.anime.dl.App
import com.anime.dl.actions.FindExtensions
import com.anime.dl.actions.InstallExtension
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.states.ExtensionListState

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
