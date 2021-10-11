package com.anime.dl.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anime.dl.R
import com.anime.dl.databinding.MainBinding
import com.anime.dl.interfaces.Tutorial
import com.anime.dl.reducers.appStateReducer
import com.anime.dl.states.AppState
import com.anime.dl.ui.base.controller.PlaceholderController
import com.anime.dl.ui.browse.BrowseController
import com.anime.dl.ui.library.LibraryController
import com.anime.dl.ui.settings.SettingsController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction.with
import org.reduxkotlin.createThreadSafeStore

val mainStore = createThreadSafeStore(::appStateReducer, AppState())

class MainActivity : AppCompatActivity(), Tutorial {

    public lateinit var binding: MainBinding
    private lateinit var router: Router
    val gonnaBreak = 0
    gonnaBreak = 1

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        if (!isTaskRoot) {
            finish()
            return
        }

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        router = Conductor.attachRouter(this, binding.controllerContainer, savedInstance)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId

            val currentRoot = router.backstack.firstOrNull()
            if (currentRoot?.tag()?.toIntOrNull() != id) {
                when (id) {
                    R.id.nav_browse -> router.setRoot(with(BrowseController()))
                    R.id.nav_home -> router.setRoot(with(LibraryController()))
                    R.id.nav_downloads -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_settings -> router.setRoot(with(SettingsController()))
                }
            }
            true
        }

        if (!router.hasRootController()) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }

        tutorial(
            this, 
            binding.bottomNavigation.findViewById(R.id.nav_browse),
            R.string.browse,
            R.string.tutorial_browse
        )
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
