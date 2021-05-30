package com.anime.dl.extensions

import android.content.Context
import androidx.preference.PreferenceManager
import com.anime.dl.R
import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.Source
import com.anime.dl.sources.TutorialSource
import dalvik.system.PathClassLoader
import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.JarEntry
import java.util.jar.JarInputStream

class ExtensionManager(private val context: Context) {

    var installedExtensions = mutableListOf<Extension.Installed>()
    var availableExtensions = mutableListOf<Extension.Available>()
    var prefs = PreferenceManager.getDefaultSharedPreferences(context)

    init {
        initExtensions()
    }

    private fun initTutorialExtension() {
        if (prefs.getBoolean("tutorial_installed", false)) {
            installedExtensions
                .add(
                    Extension.Installed(
                        name = context.resources!!.getString(R.string.tutorial_source),
                        pkgName = "com.anime.dl.tutorial",
                        lang = "all",
                        versionName = "0.0",
                        versionCode = 1,
                        isTutorial = true
                    )
                )

            installedExtensions
                .add(
                    Extension.Installed(
                        name="ryuanime",
                        pkgName="com.anime.dl.sources.RyuAnime",
                        lang="en",
                        versionName="0.0",
                        versionCode=1,
                        isTutorial=false
                    )
                )
        } else {
            availableExtensions
                .add(
                    Extension.Available(
                        name = context.resources!!.getString(R.string.tutorial_source),
                        pkgName = "com.anime.dl.tutorial",
                        lang = "all",
                        versionName = "0.0",
                        versionCode = 1,
                        isTutorial = true
                    )
                )
        }
    }

    private fun initExtensions() {
        if (!prefs.getBoolean("tutorial_complete", false)) {
            initTutorialExtension()
        }
    }

    public fun installExtension(extension: Extension) {
        if (extension.isTutorial) {
            prefs.edit().putBoolean("tutorial_installed", true).apply()
        }
    }

    public fun getExtension(pkgName: String): Extension? {
        for (extension in installedExtensions) {
            if (extension.pkgName == pkgName) return extension
        }

        return null
    }

    public fun getSource(pkgName: String): Source? {
        if (pkgName == "com.anime.dl.tutorial") {
            return TutorialSource()
        } else {
            val file = File(context.filesDir, "${pkgName}.dex")
            if (file.exists() && file.canRead()) {
                return getSourceFromJar(pkgName, file)
            }
        }

        return null
    }

    fun getSourceFromJar(pkgName: String, file: File): Source {
        val loader = PathClassLoader("${pkgName}.dex", null, context.classLoader)

        return Class.forName(pkgName, false, loader) as Source
    }

}
