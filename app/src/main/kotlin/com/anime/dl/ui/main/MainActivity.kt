package com.anime.dl.ui.main

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
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

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

        highlightView(binding.bottomNavigation)
    }

    override fun newState(state: AppState) {
    }

    private fun highlightView(view: View) {
        MaterialShowcaseView.Builder(this)
		    .setTarget(view)
		    .setDismissText("GOT IT")
		    .setContentText("This is a test")
		    .setDelay(5)
		    .singleUse(1)
		    .show();
    }
}
