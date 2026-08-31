package com.rijana.fitme.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object : Migration(3, 4) {

    override fun migrate(database: SupportSQLiteDatabase) {

        // -----------------------------------------
        // workouts
        // -----------------------------------------

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS workouts (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                userId INTEGER NOT NULL,
                name TEXT NOT NULL,
                description TEXT,
                createdAt INTEGER NOT NULL,
                isCustom INTEGER NOT NULL,
                FOREIGN KEY(userId) REFERENCES users(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workouts_userId ON workouts(userId)"
        )

        // -----------------------------------------
        // exercises
        // -----------------------------------------

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS exercises (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                description TEXT,
                muscleGroup TEXT,
                equipment TEXT,
                imageUrl TEXT
            )
            """.trimIndent()
        )

        // -----------------------------------------
        // workout_exercises
        // -----------------------------------------

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS workout_exercises (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                workoutId INTEGER NOT NULL,
                exerciseId INTEGER NOT NULL,
                sets INTEGER NOT NULL,
                reps INTEGER NOT NULL,
                orderIndex INTEGER NOT NULL,
                FOREIGN KEY(workoutId) REFERENCES workouts(id) ON DELETE CASCADE,
                FOREIGN KEY(exerciseId) REFERENCES exercises(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workout_exercises_workoutId ON workout_exercises(workoutId)"
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workout_exercises_exerciseId ON workout_exercises(exerciseId)"
        )

        // -----------------------------------------
        // workout_logs
        // -----------------------------------------

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS workout_logs (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                userId INTEGER NOT NULL,
                workoutId INTEGER NOT NULL,
                exerciseId INTEGER NOT NULL,
                setsCompleted INTEGER NOT NULL,
                repsCompleted INTEGER NOT NULL,
                weightUsed REAL,
                completedAt INTEGER NOT NULL,
                FOREIGN KEY(userId) REFERENCES users(id) ON DELETE CASCADE,
                FOREIGN KEY(workoutId) REFERENCES workouts(id) ON DELETE CASCADE,
                FOREIGN KEY(exerciseId) REFERENCES exercises(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workout_logs_userId ON workout_logs(userId)"
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workout_logs_workoutId ON workout_logs(workoutId)"
        )

        database.execSQL(
            "CREATE INDEX IF NOT EXISTS index_workout_logs_exerciseId ON workout_logs(exerciseId)"
        )
    }
}