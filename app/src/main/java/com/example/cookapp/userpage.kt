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

class userpage : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_userpage)

        // Initialize Views
        drawerLayout = findViewById(R.id.drawerLayout)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationDrawer)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 1. HANDLE BURGER ICON CLICK (OPEN DRAWER) ---
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // --- 2. HANDLE DRAWER ITEM CLICKS ---
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
                    Toast.makeText(this, "Signing Out...", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // --- 3. HANDLE BOTTOM NAV LOGIC ---
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Already on home/userpage
                    true
                }
                R.id.nav_my_recipes -> {
                    startActivity(Intent(this, MyRecipes::class.java))
                    true
                }
                R.id.nav_cookbook -> {
                    startActivity(Intent(this, Uploadrecipe::class.java))
                    true
                }
                else -> false
            }
        }
    }
}