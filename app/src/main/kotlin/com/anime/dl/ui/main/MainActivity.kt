package com.anime.dl.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anime.dl.databinding.MainBinding
import com.anime.dl.R
import com.anime.dl.reducers.extensionListReducer
import com.anime.dl.states.AppState
import com.anime.dl.ui.base.controller.PlaceholderController
import com.anime.dl.ui.browse.BrowseController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction.with
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

val mainStore = Store(
    reducer = ::extensionListReducer,
    state = null
)

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    public lateinit var binding: MainBinding
    private lateinit var router: Router
    private var currentToast: Toast? = null

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

        highlightView(binding.bottomNavigation.getChildAt(0))
    }

    override fun newState(state: AppState) {
    }

    private fun highlightView(view: View) {
        val targets: List<Target> = listOf(
            Target.Builder()
                .setShape(Circle(200f))
                .setOnTargetListener(object : OnTargetListener {
                    override fun onStarted() {
                        currentToast?.cancel()
                        currentToast = Toast.makeText(
                            this@MainActivity,
                            "this is a test",
                            5
                        )
                        currentToast?.show()
                    }

                    override fun onEnded() {
                        currentToast?.cancel()
                        currentToast = Toast.makeText(
                            this@MainActivity,
                            "test",
                            5
                        )
                        currentToast?.show()
                    }
                })
                .build()
            )

        val spotlight = Spotlight.Builder(this@MainActivity)
            .setTargets(targets)
            .setBackgroundColorRes(Color.BLACK)
            .setDuration(1000L)
            .setAnimation(DecelerateInterpolator(2f))
            .setOnSpotlightListener(object : OnSpotlightListener {
                override fun onStarted() {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(
                        this@MainActivity,
                        "spotlight test",
                        5
                    )
                    currentToast?.show()
                }

                override fun onEnded() {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(
                        this@MainActivity,
                        "spotlight test end",
                        5
                    )
                    currentToast?.show()
                }
            })
            .build()

            spotlight.start()
        }
}
