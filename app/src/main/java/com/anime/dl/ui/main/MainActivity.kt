package com.anime.dl.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anime.dl.R
import com.anime.dl.databinding.ExtensionCardItemBinding
import com.anime.dl.databinding.MainBinding
import com.anime.dl.reducers.appStateReducer
import com.anime.dl.states.AppState
import com.anime.dl.ui.base.controller.PlaceholderController
import com.anime.dl.ui.browse.BrowseController
import com.anime.dl.ui.browse.extension.ExtensionItem
import com.anime.dl.ui.settings.SettingsController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction.with
import org.reduxkotlin.createThreadSafeStore
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig
import uk.co.deanwild.materialshowcaseview.shape.OvalShape
import uk.co.deanwild.materialshowcaseview.shape.Shape

val mainStore = createThreadSafeStore(::appStateReducer, AppState())

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

        tutorial()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId

            val currentRoot = router.backstack.firstOrNull()
            if (currentRoot?.tag()?.toIntOrNull() != id) {
                when (id) {
                    R.id.nav_browse -> router.setRoot(with(BrowseController()))
                    R.id.nav_home -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_downloads -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_settings -> router.setRoot(with(SettingsController()))
                }
            }
            true
        }

        if (!router.hasRootController()) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    private lateinit var downloadExtButton: View
    private lateinit var extensionBinding: ExtensionCardItemBinding
    private lateinit var installExtListener: MaterialShowcaseSequence.OnSequenceItemDismissedListener
    private val controller: BrowseController = BrowseController()

    private fun tutorial() {
        binding.bottomNavigation.selectedItemId = R.id.nav_browse
        router.setRoot(with(controller))

        installExtListener =
            MaterialShowcaseSequence.OnSequenceItemDismissedListener() { _, _ ->
                downloadExtButton.performClick()

                tutorial(
                    listOf(
                        Pair(
                            extensionBinding.root, R.string.tutorial_click_source
                        )
                    ),
                    null,
                    3,
                    OvalShape()
                )
            }

        extensionTutorial()
    }

    private fun extensionTutorial() {
        // Displays extension download button
        val extListener =
            MaterialShowcaseSequence.OnSequenceItemDismissedListener() { _, _ ->
                extensionBinding =
                    (
                        controller
                            .itemAdapter
                            .getAdapterItem(1) as ExtensionItem
                        ).binding

                downloadExtButton = extensionBinding.extButton

                tutorial(
                    listOf(
                        Pair(downloadExtButton, R.string.tutorial_download_ext)
                    ),
                    installExtListener,
                    2
                )
            }

        tutorial(
            listOf(
                Pair(
                    binding.bottomNavigation.findViewById(R.id.nav_browse),
                    R.string.tutorial_browse
                ),
            ),
            extListener,
            1
        )
    }

    private fun tutorial(
        viewsAndTutorialStrings: List<Pair<View, Int>>,
        listener: MaterialShowcaseSequence.OnSequenceItemDismissedListener?,
        position: Int,
        shape: Shape? = null
    ) {
        val config: ShowcaseConfig = ShowcaseConfig()
        config.delay = 500
        config.renderOverNavigationBar = true

        if (shape != null) {
            config.shape = shape
        }

        val sequence: MaterialShowcaseSequence =
            MaterialShowcaseSequence(this@MainActivity, "tutorial_$position")
        sequence.setConfig(config)

        viewsAndTutorialStrings.forEach { item ->
            sequence.addSequenceItem(
                item.first, resources!!.getString(item.second), resources!!.getString(R.string.tutorial_understood)
            )
        }

        sequence.setOnItemDismissedListener(listener)

        sequence.start()
    }
}
