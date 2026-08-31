package com.rijana.fitme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.rijana.fitme.database.DatabaseProvider
import com.rijana.fitme.database.entity.User
import com.rijana.fitme.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.launch

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        // -----------------------------------------
        // Sign In button
        // -----------------------------------------

        btnSignIn.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // -----------------------------------------
            // Check the password against Firebase
            // -----------------------------------------

            btnSignIn.isEnabled = false

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        val firebaseUser = auth.currentUser

                        if (firebaseUser == null) {

                            btnSignIn.isEnabled = true

                            Toast.makeText(
                                this,
                                "Signed in, but user information could not be retrieved.",
                                Toast.LENGTH_LONG
                            ).show()

                            return@addOnCompleteListener
                        }

                        handleSuccessfulSignIn(
                            firebaseUid = firebaseUser.uid,
                            email = firebaseUser.email,
                            btnSignIn = btnSignIn
                        )

                    } else {

                        // -----------------------------------------
                        // Firebase rejected the email/password
                        // -----------------------------------------

                        btnSignIn.isEnabled = true

                        Toast.makeText(
                            this,
                            "Incorrect email or password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        // -----------------------------------------
        // Don't have an account?
        // -----------------------------------------

        tvSignUp.setOnClickListener {

            startActivity(Intent(this, SignUp::class.java))
        }
    }

    // ==========================================
    // FIREBASE CONFIRMED THE PASSWORD IS CORRECT —
    // now find/build the local Room user and decide
    // where to send them (Onboarding vs Home).
    // ==========================================

    private fun handleSuccessfulSignIn(
        firebaseUid: String,
        email: String?,
        btnSignIn: Button
    ) {

        val database = DatabaseProvider.getDatabase(this)

        lifecycleScope.launch {

            try {

                var user = database.userDao()
                    .getUserByFirebaseUid(firebaseUid)

                // If the Room row is missing (e.g. app storage was
                // cleared but the Firebase account still exists),
                // recreate a minimal local record so sign-in can continue.
                if (user == null) {

                    database.userDao().insertUser(
                        User(
                            firebaseUid = firebaseUid,
                            name = email ?: "User",
                            email = email ?: ""
                        )
                    )

                    user = database.userDao()
                        .getUserByFirebaseUid(firebaseUid)
                }

                if (user == null) {

                    btnSignIn.isEnabled = true

                    Toast.makeText(
                        this@SignIn,
                        "Sign in failed. Please try again.",
                        Toast.LENGTH_LONG
                    ).show()

                    return@launch
                }

                // Remember this login for next time the app opens
                getSharedPreferences("FitMePrefs", MODE_PRIVATE).edit()
                    .putBoolean("loggedIn", true)
                    .apply()

                Toast.makeText(
                    this@SignIn,
                    "Login successful!",
                    Toast.LENGTH_SHORT
                ).show()

                // Has this user already finished onboarding?
                val profile = database.userProfileDao()
                    .getUserProfileByUserId(user.id)

//                if (profile == null) {
//
//                    startActivity(
//                        Intent(this@SignIn, OnboardingActivity::class.java)
//                    )
//
//                } else {

                    startActivity(
                        Intent(this@SignIn, Home::class.java)
                    )
//                }

                finish()

            } catch (e: Exception) {

                btnSignIn.isEnabled = true

                Toast.makeText(
                    this@SignIn,
                    "Sign in failed: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}