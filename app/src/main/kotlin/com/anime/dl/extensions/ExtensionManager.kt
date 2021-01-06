package com.anime.dl.extensions

import android.content.Context
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.anime.dl.extensions.models.Extension
import com.anime.dl.R

class ExtensionManager(private val context: Context) {

    var installedExtensions = emptyList<Extension.Installed>()
    var availableExtensions = emptyList<Extension.Available>()
    var prefs = PreferenceManager.getDefaultSharedPreferences(context)

    init {
        initExtensions()
        Toast.makeText(context, "this is a test", 5).show()
    }

    private fun initTutorialExtension() {
        if (prefs.getBoolean("tutorial_installed", false)) {
            installedExtensions = listOf(
                Extension.Installed(
                    name = context.resources!!.getString(R.string.tutorial_extension),
                    pkgName = "com.anime.dl.tutorial",
                    lang = "all",
                    versionName = "0.0",
                    versionCode = 1
                )
            )
        } else {
            availableExtensions = listOf(
                Extension.Available(
                    name = context.resources!!.getString(R.string.tutorial_extension),
                    pkgName = "com.anime.dl.tutorial",
                    lang = "all",
                    versionName = "0.0",
                    versionCode = 1
                )
            )
        }
    }

    private fun initExtensions() {
        if (!prefs.getBoolean("tutorial_complete", false)) {
            initTutorialExtension()
        }
    }
}
