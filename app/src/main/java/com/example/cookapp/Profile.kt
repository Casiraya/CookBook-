package com.example.cookapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText

class Profile : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    // UI Elements
    private lateinit var etName: TextInputEditText
    private lateinit var etBio: TextInputEditText
    private lateinit var btnAction: Button

    // Track if we are in "Editing Mode"
    private var isEditing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // 1. Initialize Views
        drawerLayout = findViewById(R.id.drawerLayout)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationDrawer)
        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)

        etName = findViewById(R.id.etName)
        etBio = findViewById(R.id.etBio)
        btnAction = findViewById(R.id.btnAction)

        // 2. Handle System Bar Padding
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Set Initial State (Read-only)
        setEditingEnabled(false)

        // 4. Handle Drawer Menu
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, userpage::class.java)
                    startActivity(intent)
                    finish()
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

        // 5. "Update" / "Save Changes" Button Logic
        btnAction.setOnClickListener {
            if (!isEditing) {
                // SWITCH TO EDIT MODE
                isEditing = true
                setEditingEnabled(true)
                btnAction.text = "Save Changes"
                etName.requestFocus()
            } else {
                // SAVE CHANGES
                val newName = etName.text.toString().trim()

                if (newName.isEmpty()) {
                    etName.error = "Name cannot be empty"
                } else {
                    Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show()

                    // SWITCH BACK TO READ-ONLY MODE
                    isEditing = false
                    setEditingEnabled(false)
                    btnAction.text = "Update"
                }
            }
        }
    }

    private fun setEditingEnabled(enabled: Boolean) {
        etName.isEnabled = enabled
        etBio.isEnabled = enabled
    }
}