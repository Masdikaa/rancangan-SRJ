package com.masdika.tessmartrescuejacket

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.masdika.tessmartrescuejacket.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Inisiasi Menu Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_fragment_layout, HomeFragment()).commit()

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("Bottom_Bar", "Selected index: $newIndex, title: ${newTab.title}")
                when (newTab.id) {
                    // Get ID from nav_menu
                    R.id.tab_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_fragment_layout, HomeFragment()).commit()
                    }

                    R.id.tab_history -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_fragment_layout, HistoryFragment()).commit()
                    }

                }
            }
        })

    }
}