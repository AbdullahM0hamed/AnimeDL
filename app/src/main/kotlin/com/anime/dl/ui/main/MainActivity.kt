package com.anime.dl.ui.main

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.anime.dl.R
import com.anime.dl.databinding.MainBinding
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router

class MainActivity : Activity() {

    private lateinit var binding: MainBinding
    private lateinit var router: Router

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        if (!isTaskRoot) {
            finish()
            return
        }

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = Conductor.attachRouter(this, binding.controllerContainer, savedInstance)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val toast = Toast.makeText(applicationContext, "test", 5)
            toast.show()
            true
        }
    }
}
