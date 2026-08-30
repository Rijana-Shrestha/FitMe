package com.rijana.fitme.database

import android.content.Context
import androidx.room.Room
import com.rijana.fitme.database.migration.MIGRATION_1_2

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
                .addMigrations(MIGRATION_1_2)
                .build()

            INSTANCE = instance

            instance
        }
    }
}