package com.rijana.fitme

import android.app.Application
import com.rijana.fitme.database.DatabaseProvider
import com.rijana.fitme.database.ExerciseSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FitMeApplication : Application() {

    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        seedExercises()
    }

    private fun seedExercises() {

        val database = DatabaseProvider.getDatabase(this)

        applicationScope.launch {

            ExerciseSeeder.seedExercises(
                database.exerciseDao()
            )
        }
    }
}