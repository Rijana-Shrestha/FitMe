package com.rijana.fitme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.rijana.fitme.database.DatabaseProvider
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // --- ADD THIS BLOCK TO TRIGGER ROOM CONNECTION ---
        val database = DatabaseProvider.getDatabase(this)
        lifecycleScope.launch {
            // Triggering a read operation opens the SQLite connection
            val users = database.userDao().getAllUsers()
            Log.d("FITME_DATABASE", "Fetched ${users.size} users")
        }

        val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)
        val loggedIn = preferences.getBoolean("loggedIn", false)

        if (loggedIn) {
            startActivity(Intent(this, Home::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_welcome)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)

        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }


    }
}