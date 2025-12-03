package com.example.cookapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MyRecipes : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_recipes)

        // 1. Initialize Views
        drawerLayout = findViewById(R.id.drawerLayout)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationDrawer)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)

        // 2. Handle Edge-to-Edge Padding
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Drawer Toggle
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // 4. Drawer Navigation Logic
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    startActivity(Intent(this, Profile::class.java))
                }
                R.id.nav_about -> {
                    // Navigate to About Us Page
                    startActivity(Intent(this, AboutUs::class.java))
                }
                R.id.nav_signout -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 5. Bottom Navigation Logic
        bottomNav.selectedItemId = R.id.nav_my_recipes // Highlight "My Recipes"
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, userpage::class.java))
                    finish()
                    true
                }
                R.id.nav_my_recipes -> {
                    // Already on My Recipes page
                    true
                }
                R.id.nav_cookbook -> {
                    startActivity(Intent(this, Uploadrecipe::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}