package com.example.cookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cookapp.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    private lateinit var bind: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // SIGN UP BUTTON
        bind.btnSignUp.setOnClickListener {
            val first = bind.etFirstName.text.toString().trim()
            val last = bind.etLastName.text.toString().trim()
            val email = bind.etEmail.text.toString().trim()
            val pass = bind.etPassword.text.toString().trim()

            if (first.isEmpty() || last.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // No actual account creation — just UI flow
                Toast.makeText(this, "Account created (demo mode)", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // SWITCH TO LOGIN
        bind.tvSwitchLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
