package com.rijana.fitme.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL(
            "ALTER TABLE users ADD COLUMN profileImage TEXT"
        )

        database.execSQL(
            "ALTER TABLE users ADD COLUMN bio TEXT"
        )

        database.execSQL(
            "ALTER TABLE users ADD COLUMN createdAt INTEGER NOT NULL DEFAULT 0"
        )
    }
}