package com.corsolp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.corsolp.ui.databinding.ActivityMainBinding
import com.corsolp.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, HomeFragment())
                .commit()
        }
    }
}