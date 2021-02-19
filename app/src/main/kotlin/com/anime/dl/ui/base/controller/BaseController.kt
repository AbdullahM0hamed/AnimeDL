package com.anime.dl.ui.base.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.anime.dl.ui.main.MainActivity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RestoreViewOnCreateController

abstract class BaseController<VB : ViewBinding>(bundle: Bundle? = null) :
    RestoreViewOnCreateController(bundle) {

    public lateinit var binding: VB
    var actionBar: ActionBar? = null
    open val hasBottomNav = true

    init {
        addLifecycleListener(
            object : LifecycleListener() {
                override fun postCreateView(controller: Controller, view: View) {
                    onViewCreated(view)
                }
            })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        return inflateView(inflater, container)
    }

    override fun onAttach(view: View) {
        setActionBar()
        if (!hasBottomNav) hideBottomNav()
    }

    override fun onDetach(view: View) {
        resetActionBar()
        (activity as? MainActivity)?.binding?.bottomNavigation?.visibility = View.VISIBLE
    }

    abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    open fun onViewCreated(view: View) {
        actionBar = (activity as? AppCompatActivity)?.supportActionBar
        setActionBar()
        if (!hasBottomNav) hideBottomNav()
    }

    open fun setActionBar() {}

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        if (type.isEnter) {
            setTitle()
        }

        super.onChangeStarted(handler, type)
    }

    open fun getTitle(): String? {
        return null
    }

    fun setTitle(title: String? = null) {
        var parentController = parentController
        while (parentController != null) {
            if (parentController is BaseController<*> && parentController.getTitle() != null) {
                return
            }
            parentController = parentController.parentController
        }

        (activity as? AppCompatActivity)?.supportActionBar?.title = title ?: getTitle()
    }

    fun resetActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun hideBottomNav() {
        (activity as? MainActivity)?.binding?.bottomNavigation?.visibility = View.GONE
    }
}
