package com.anime.dl.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anime.dl.databinding.MainBinding
import com.anime.dl.R
import com.anime.dl.ui.base.controller.PlaceholderController
import com.anime.dl.ui.browse.BrowseController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction.with

class MainActivity : AppCompatActivity() {

    public lateinit var binding: MainBinding
    private lateinit var router: Router

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

        if (!router.hasRootController()) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId

            val currentRoot = router.backstack.firstOrNull()
            if (currentRoot?.tag()?.toIntOrNull() != id) {
                when (id) {
                    R.id.nav_browse -> router.setRoot(with(BrowseController()))
                    R.id.nav_home -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_downloads -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_settings -> router.setRoot(with(PlaceholderController()))
                }
            }
            true
        }
    }
}
