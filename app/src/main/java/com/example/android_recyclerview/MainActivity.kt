package com.example.android_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.android_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fl_main_layout, AlarmFragment()).commit()

        binding.navigationMain.setOnItemSelectedListener {
            replaceFragment(
                when(it.itemId) {
                    R.id.navigation_alarm -> AlarmFragment()
                    R.id.navigation_sleep -> SleepFragment()
                    R.id.navigation_favorite -> FavoriteFragment()
                    R.id.navigation_news -> NewsFragment()
                    else -> MoreFragment()
                }
            )
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_layout, fragment).commit()
    }
}