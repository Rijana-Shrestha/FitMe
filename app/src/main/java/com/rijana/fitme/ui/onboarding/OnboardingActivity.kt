package com.rijana.fitme.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.rijana.fitme.MainActivity
import com.rijana.fitme.R
import com.rijana.fitme.database.DatabaseProvider
import com.rijana.fitme.database.entity.UserProfile
import kotlinx.coroutines.launch

class OnboardingActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnContinue: Button
    private lateinit var tvSkip: TextView
    private lateinit var progressBar: ProgressBar

    // Onboarding information
    private var selectedGender: String? = null
    private var heightFeet: Int? = null
    private var heightInches: Int? = null
    private var weight: Double? = null
    private var selectedGoal: String? = null

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val database by lazy {
        DatabaseProvider.getDatabase(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        btnBack = findViewById(R.id.btnBack)
        btnContinue = findViewById(R.id.btnContinue)
        tvSkip = findViewById(R.id.tvSkip)
        progressBar = findViewById(R.id.progressBar)

        // Show Gender screen first
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.onboardingFragmentContainer,
                    OnboardingGenderFragment()
                )
                .commit()
            // Step 1
            progressBar.progress = 33
        }

        setupBackButton()
        setupContinueButton()
        setupSkipButton()
        setupProgressBar()
    }

    // ==========================================
    // BACK BUTTON
    // ==========================================

    private fun setupBackButton() {
        btnBack.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    // ==========================================
    // PROGRESS BAR
    // ==========================================

    private fun setupProgressBar() {
        // Update progress whenever the displayed fragment changes
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(
                R.id.onboardingFragmentContainer
            )

            when (currentFragment) {
                is OnboardingGenderFragment -> {
                    progressBar.progress = 33
                }

                is OnboardingHeightWeightFragment -> {
                    progressBar.progress = 66
                }

                is OnboardingGoalFragment -> {
                    progressBar.progress = 100
                }
            }
        }
    }

    // ==========================================
    // CONTINUE BUTTON
    // ==========================================

    private fun setupContinueButton() {
        btnContinue.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(
                R.id.onboardingFragmentContainer
            )

            // =====================================
            // STEP 1 — GENDER
            // =====================================
            if (currentFragment is OnboardingGenderFragment) {
                val gender = currentFragment.getSelectedGender()

                if (gender == null) {
                    Toast.makeText(
                        this,
                        "Please select your gender",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    selectedGender = gender
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.onboardingFragmentContainer,
                            OnboardingHeightWeightFragment()
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }

            // =====================================
            // STEP 2 — HEIGHT & WEIGHT
            // =====================================
            else if (currentFragment is OnboardingHeightWeightFragment) {
                val feet = currentFragment.getHeightFeet()
                val inches = currentFragment.getHeightInches()
                val userWeight = currentFragment.getWeight()

                if (feet == null || inches == null || userWeight == null) {
                    Toast.makeText(
                        this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    heightFeet = feet
                    heightInches = inches
                    weight = userWeight

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.onboardingFragmentContainer,
                            OnboardingGoalFragment()
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }

            // =====================================
            // STEP 3 — GOAL
            // =====================================
            else if (currentFragment is OnboardingGoalFragment) {
                val goal = currentFragment.getSelectedGoal()

                if (goal == null) {
                    Toast.makeText(
                        this,
                        "Please select your goal",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    selectedGoal = goal
                    saveUserProfile()
                }
            }
        }
    }

    // ==========================================
    // SAVE PROFILE TO ROOM & NAVIGATE HOME
    // ==========================================

    private fun saveUserProfile() {
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            Toast.makeText(
                this,
                "User not found. Please sign in again.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        lifecycleScope.launch {
            try {
                // Find the Room user using Firebase UID
                val user = database.userDao().getUserByFirebaseUid(firebaseUser.uid)

                if (user == null) {
                    Toast.makeText(
                        this@OnboardingActivity,
                        "User record not found.",
                        Toast.LENGTH_LONG
                    ).show()
                    return@launch
                }

                // Create UserProfile
                val profile = UserProfile(
                    userId = user.id,
                    gender = selectedGender!!,
                    heightFeet = heightFeet!!,
                    heightInches = heightInches!!,
                    weight = weight!!,
                    goal = selectedGoal!!
                )

                // Save to Room
                database.userProfileDao().insertUserProfile(profile)

                // 1. Mark user as logged in so MainActivity knows to display the main app shell
                val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)
                preferences.edit().putBoolean("loggedIn", true).apply()

                Toast.makeText(
                    this@OnboardingActivity,
                    "Profile saved successfully!",
                    Toast.LENGTH_SHORT
                ).show()

                // 2. Clear activity stack and navigate to MainActivity
                val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

            } catch (e: Exception) {
                Toast.makeText(
                    this@OnboardingActivity,
                    "Failed to save profile: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // ==========================================
    // SKIP
    // ==========================================

    private fun setupSkipButton() {
        tvSkip.setOnClickListener {
            // Save login flag on skip as well
            val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)
            preferences.edit().putBoolean("loggedIn", true).apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}