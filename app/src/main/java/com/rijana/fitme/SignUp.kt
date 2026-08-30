package com.rijana.fitme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnSignUp.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (password != confirmPassword) {

                Toast.makeText(
                    this,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)

                preferences.edit()
                    .putString("email", email)
                    .putString("password", password)
                    .putBoolean("loggedIn", true)
                    .apply()

                Toast.makeText(
                    this,
                    "Account created successfully!",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, Onboarding1::class.java))

                finish()
            }
        }

        tvLogin.setOnClickListener {

            startActivity(Intent(this, SignIn::class.java))
        }
    }
}