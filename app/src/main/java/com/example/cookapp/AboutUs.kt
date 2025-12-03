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

class AboutUs : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_us)

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
                    // finish() // Optional: depends if you want to keep stack
                }
                R.id.nav_about -> {
                    // Already on About Us page
                    drawerLayout.closeDrawer(GravityCompat.START)
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
        // Since "About Us" isn't a bottom tab item, we don't highlight any item by default
        // or you could clear selection if needed: bottomNav.menu.setGroupCheckable(0, true, false)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, userpage::class.java))
                    finish()
                    true
                }
                R.id.nav_my_recipes -> {
                    startActivity(Intent(this, MyRecipes::class.java))
                    finish()
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