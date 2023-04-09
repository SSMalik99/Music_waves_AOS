package com.example.music_waves_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.music_waves_aos.databinding.ActivityHomeScreenBinding
import com.example.music_waves_aos.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController

class HomeScreen : AppCompatActivity() {

    lateinit var binding : ActivityHomeScreenBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeScreenBinding.inflate(layoutInflater)

        val view = binding.root



        setupNavigation()

        setContentView(view)
    }

    fun setupNavigation() {

        navHostFragment =supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

//        setupWithNavController(bottomNavigationView, navController)
//        setupActionBarWithNavController(navController)
//        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
//            when(item.itemId) {
//                R.id.item1 -> {
//                    // Respond to navigation item 1 reselection
//                }
//                R.id.item2 -> {
//                    // Respond to navigation item 2 reselection
//                }
//            }
//        }
    }
}