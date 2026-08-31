package com.rijana.fitme.database

import android.content.Context
import androidx.room.Room
import com.rijana.fitme.database.migration.MIGRATION_1_2
import com.rijana.fitme.database.migration.MIGRATION_2_3
import com.rijana.fitme.database.migration.MIGRATION_3_4
import com.rijana.fitme.database.entity.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {

    @Volatile
    private var INSTANCE: FitMeDatabase? = null

    fun getDatabase(context: Context): FitMeDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                FitMeDatabase::class.java,
                "fitme_database"
            )
                .addMigrations(
                    MIGRATION_1_2,
                    MIGRATION_2_3,
                    MIGRATION_3_4
                )
                .build()

            INSTANCE = instance

            // Seed exercises
            CoroutineScope(Dispatchers.IO).launch {

                val exerciseDao = instance.exerciseDao()

                val existingExercises =
                    exerciseDao.getAllExercises()

                if (existingExercises.isEmpty()) {

                    ExerciseSeedData.exercises.forEach { exercise ->
                        exerciseDao.insertExercise(exercise)
                    }
                }
            }

            instance
        }
    }
}