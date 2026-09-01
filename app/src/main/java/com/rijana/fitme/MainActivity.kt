package com.rijana.fitme

import android.content.Intent
//import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rijana.fitme.database.DatabaseProvider
import com.rijana.fitme.ui.auth.SignUp
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge drawing
        enableEdgeToEdge()

        // Set status bar background color to match app background
        window.statusBarColor = ContextCompat.getColor(this, R.color.black) // Or use Color.parseColor("#0D0D0D")

        // Force status bar icons (battery, wifi, time) to render in white
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false

        val database = DatabaseProvider.getDatabase(this)
        lifecycleScope.launch {
            val users = database.userDao().getAllUsers()
            Log.d("FITME_DATABASE", "Fetched ${users.size} users")
        }

        val preferences = getSharedPreferences("FitMePrefs", MODE_PRIVATE)
        val loggedIn = preferences.getBoolean("loggedIn", false)

        if (loggedIn) {
            setContentView(R.layout.activity_main)
            setupBottomNavigation()
            setupWindowInsets()
            return
        }

        setContentView(R.layout.activity_welcome)
        setupWindowInsets()

        findViewById<Button>(R.id.btnGetStarted)?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment ?: return
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.chatDetailFragment -> {
                    bottomNav?.visibility = View.GONE
                }
                else -> {
                    bottomNav?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupWindowInsets() {
        val rootLayout = findViewById<View>(R.id.main) ?: findViewById<View>(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val navigationBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

            // Padding top ensures app content starts right below the status bar
            view.setPadding(0, statusBarInsets.top, 0, navigationBarInsets.bottom)
            insets
        }
    }
}