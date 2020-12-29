package com.anime.dl.ui.main

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.anime.dl.R
import com.anime.dl.databinding.MainBinding

class MainActivity : Activity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val toast = Toast.makeText(applicationContext, "test", 5)
            toast.show()
            true
        }
    }
}
