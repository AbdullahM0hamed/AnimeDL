package com.anime.dl.extensions.models

sealed class Extension {

    abstract val name: String
    abstract val pkgName: String
    abstract val versionName: String
    abstract val versionCode: Int
    abstract val lang: String
    abstract val isTutorial: Boolean

    data class Installed(
        override val name: String,
        override val pkgName: String,
        override val lang: String,
        override val versionName: String,
        override val versionCode: Int,
        override var isTutorial: Boolean,
        val hasUpdate: Boolean = false,
        val isObsolete: Boolean = false
    ) : Extension()

    data class Available(
        override val name: String,
        override val pkgName: String,
        override val lang: String,
        override val versionName: String,
        override val versionCode: Int,
        override var isTutorial: Boolean
    ) : Extension()
}
