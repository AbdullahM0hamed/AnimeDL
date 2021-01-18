package com.anime.dl.ui.main

import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anime.dl.App
import com.anime.dl.databinding.MainBinding
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.R
import com.anime.dl.reducers.extensionListReducer
import com.anime.dl.states.ExtensionListState
import com.anime.dl.ui.base.controller.PlaceholderController
import com.anime.dl.ui.base.controller.TabbedController
import com.anime.dl.ui.browse.BrowseController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction.with
import org.reduxkotlin.createThreadSafeStore
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig

val mainStore = createThreadSafeStore(
    ::extensionListReducer,
    ExtensionListState()
)

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
                clearContainersAndChangeRouter(false)
                when (id) {
                    R.id.nav_browse -> {
                        router = Conductor.attachRouter(this, binding.browseContainer, savedInstance)
                        router.setRoot(with(BrowseController()))
                    }
                    R.id.nav_home -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_downloads -> router.setRoot(with(PlaceholderController()))
                    R.id.nav_settings -> router.setRoot(with(PlaceholderController()))
                }
            }
            true
        }

        if (!router.hasRootController()) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun tutorial() {
        binding.bottomNavigation.selectedItemId = R.id.nav_browse
        clearContainersAndChangeRouter(true)

        val controller: BrowseController = BrowseController()
        router.setRoot(with(controller))

        val listener = MaterialShowcaseSequence.OnSequenceItemDismissedListener() { itemView, position ->
            if (position == 1) {
                controller.binding.pager.currentItem = 1
            }
        }

        tutorial(
            listOf(
                Pair(
                    binding.bottomNavigation.findViewById(R.id.nav_browse),
                    resources!!.getString(R.string.tutorial_browse)
                ),
                Pair(
                    (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(1),
                    resources.getString(R.string.tutorial_extension_tab)
                )
            ),
            listener
        )
    }

    private fun tutorial(viewsAndTutorialStrings: List<Pair<View, String>>, listener: MaterialShowcaseSequence.OnSequenceItemDismissedListener) {
        val config: ShowcaseConfig = ShowcaseConfig()
        config.delay = 500
        config.renderOverNavigationBar = true

        val sequence: MaterialShowcaseSequence = MaterialShowcaseSequence(this@MainActivity, "tutorial")
        sequence.setConfig(config)

        viewsAndTutorialStrings.forEach { item ->
            sequence.addSequenceItem(
                item.first,
                item.second,
                resources!!.getString(R.string.tutorial_understood)
            )
        }

        sequence.setOnItemDismissedListener(listener)

        sequence.start()
    }

    private fun clearContainersAndChangeRouter(isBrowse: Boolean) {
        binding.controllerContainer.removeAllViews()
        binding.browseContainer.removeAllViews()

        if (isBrowse) {
            router = Conductor.attachRouter(this, binding.browseContainer, null)
        } else {
            router = Conductor.attachRouter(this, binding.controllerContainer, null)
        }
    }
}
