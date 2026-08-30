package com.rijana.fitme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        btnSignIn.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)

            val savedEmail = preferences.getString("email", "")
            val savedPassword = preferences.getString("password", "")

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (email == savedEmail && password == savedPassword) {

                preferences.edit()
                    .putBoolean("loggedIn", true)
                    .apply()

                Toast.makeText(
                    this,
                    "Login successful!",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, Home::class.java))

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        tvSignUp.setOnClickListener {

            startActivity(Intent(this, SignUp::class.java))
        }
    }
}