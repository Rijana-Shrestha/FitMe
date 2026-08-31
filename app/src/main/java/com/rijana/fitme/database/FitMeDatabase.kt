package com.rijana.fitme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rijana.fitme.database.dao.ExerciseDao
import com.rijana.fitme.database.dao.UserDao
import com.rijana.fitme.database.dao.UserProfileDao
import com.rijana.fitme.database.dao.WorkoutDao
import com.rijana.fitme.database.dao.WorkoutExerciseDao
import com.rijana.fitme.database.dao.WorkoutLogDao
import com.rijana.fitme.database.entity.Exercise
import com.rijana.fitme.database.entity.User
import com.rijana.fitme.database.entity.UserProfile
import com.rijana.fitme.database.entity.Workout
import com.rijana.fitme.database.entity.WorkoutExercise
import com.rijana.fitme.database.entity.WorkoutLog

@Database(
    entities = [
        User::class,
        UserProfile::class,
        Workout::class,
        Exercise::class,
        WorkoutExercise::class,
        WorkoutLog::class
    ],
    version = 4
)
abstract class FitMeDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun userProfileDao(): UserProfileDao

    abstract fun workoutDao(): WorkoutDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun workoutExerciseDao(): WorkoutExerciseDao

    abstract fun workoutLogDao(): WorkoutLogDao
}