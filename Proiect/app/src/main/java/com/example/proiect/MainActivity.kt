package com.example.proiect

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Grab references
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNav    = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // Wire up bottom nav to navigation controller
        bottomNav.setupWithNavController(navController)

        // Hide on login/register; show everywhere else
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.visibility = if (
                destination.id == R.id.loginFragment ||
                destination.id == R.id.registerFragment
            ) View.GONE else View.VISIBLE
        }
    }
}
