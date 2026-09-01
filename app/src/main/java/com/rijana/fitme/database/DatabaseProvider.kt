package com.rijana.fitme.database

import android.content.Context
import androidx.room.Room
import com.rijana.fitme.database.migration.MIGRATION_1_2
import com.rijana.fitme.database.migration.MIGRATION_2_3
import com.rijana.fitme.database.migration.MIGRATION_3_4

/**
 * Provides the singleton [FitMeDatabase] instance.
 *
 * NOTE: Seeding is intentionally NOT done here. It previously ran a second,
 * concurrent seed coroutine alongside [ExerciseSeeder] (called from
 * FitMeApplication.onCreate), which raced on the "is the table empty" check
 * and could insert the 16 seed exercises twice on first launch. Seeding now
 * happens in exactly one place: ExerciseSeeder, invoked once from
 * FitMeApplication.
 */
object DatabaseProvider {

    @Volatile
    private var INSTANCE: FitMeDatabase? = null

    fun getDatabase(context: Context): FitMeDatabase {

        return INSTANCE ?: synchronized(this) {

            INSTANCE ?: Room.databaseBuilder(
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
                .also { INSTANCE = it }
        }
    }
}