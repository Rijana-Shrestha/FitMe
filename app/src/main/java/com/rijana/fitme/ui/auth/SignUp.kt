package com.rijana.fitme.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.rijana.fitme.MainActivity
import com.rijana.fitme.R
import com.rijana.fitme.database.DatabaseProvider
import com.rijana.fitme.database.entity.User
import com.rijana.fitme.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // -----------------------------------------
        // Find views from XML
        // -----------------------------------------

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword =
            findViewById<EditText>(R.id.etConfirmPassword)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        // -----------------------------------------
        // Sign Up button
        // -----------------------------------------

        btnSignUp.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val fullName = etFullName.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            // -----------------------------------------
            // Validate fields
            // -----------------------------------------

            if (
                email.isEmpty() ||
                fullName.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (password != confirmPassword) {

                Toast.makeText(
                    this,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // -----------------------------------------
            // Create account in Firebase
            // -----------------------------------------

            btnSignUp.isEnabled = false

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        // Firebase successfully created the account

                        val firebaseUser = auth.currentUser

                        if (firebaseUser == null) {

                            btnSignUp.isEnabled = true

                            Toast.makeText(
                                this,
                                "Account created, but user information could not be retrieved.",
                                Toast.LENGTH_LONG
                            ).show()

                            return@addOnCompleteListener
                        }

                        // -----------------------------------------
                        // Get Firebase UID
                        // -----------------------------------------

                        val firebaseUid = firebaseUser.uid

                        // -----------------------------------------
                        // Create Room User
                        // -----------------------------------------

                        val user = User(
                            firebaseUid = firebaseUid,
                            name = fullName,
                            email = email
                        )

                        // -----------------------------------------
                        // Insert user into Room
                        // -----------------------------------------

                        val database =
                            DatabaseProvider.getDatabase(this)

                        lifecycleScope.launch {

                            try {

                                database.userDao().insertUser(user)

                                Toast.makeText(
                                    this@SignUp,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // -----------------------------------------
                                // Go to onboarding
                                // -----------------------------------------

                                startActivity(
                                    Intent(
                                        this@SignUp,
                                        OnboardingActivity::class.java
                                    )
                                )

                                finish()

                            } catch (e: Exception) {

                                Toast.makeText(
                                    this@SignUp,
                                    "Account created, but saving user data failed.",
                                    Toast.LENGTH_LONG
                                ).show()

                                btnSignUp.isEnabled = true
                            }
                        }

                    } else {

                        // -----------------------------------------
                        // Firebase registration failed
                        // -----------------------------------------

                        btnSignUp.isEnabled = true

                        val errorMessage =
                            task.exception?.message
                                ?: "Registration failed"

                        Toast.makeText(
                            this,
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        // -----------------------------------------
        // Already have an account?
        // -----------------------------------------

        tvLogin.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    SignIn::class.java
                )
            )
        }
    }
}