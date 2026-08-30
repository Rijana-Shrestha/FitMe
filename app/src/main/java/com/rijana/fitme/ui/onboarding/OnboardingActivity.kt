package com.rijana.fitme.ui.onboarding

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rijana.fitme.R

class OnboardingActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var tvSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_onboarding)

        btnBack = findViewById(R.id.btnBack)
        tvSkip = findViewById(R.id.tvSkip)

        // Show the first onboarding screen
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.onboardingFragmentContainer,
                    OnboardingGenderFragment()
                )
                .commit()
        }

        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Skip button
        tvSkip.setOnClickListener {
            Toast.makeText(
                this,
                "Skip clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}