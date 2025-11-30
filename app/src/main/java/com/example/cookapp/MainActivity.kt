package com.example.cookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOGIN BUTTON
        binding.btnMain.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Navigate to UserPage after login
                val intent = Intent(this, userpage::class.java)
                // Optional: pass email or other data
                intent.putExtra("email", email)
                startActivity(intent)
                finish() // Prevent going back to login
            }
        }

        // SWITCH TO SIGNUP
        binding.tvToggleAuth.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }
    }
}
