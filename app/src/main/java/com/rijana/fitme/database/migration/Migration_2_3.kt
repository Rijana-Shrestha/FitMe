package com.rijana.fitme.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS user_profile (
                userId INTEGER NOT NULL,
                gender TEXT NOT NULL,
                heightFeet INTEGER NOT NULL,
                heightInches INTEGER NOT NULL,
                weight REAL NOT NULL,
                goal TEXT NOT NULL,
                PRIMARY KEY(userId),
                FOREIGN KEY(userId) REFERENCES users(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )
    }
}