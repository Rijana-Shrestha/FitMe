package com.rijana.fitme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rijana.fitme.database.dao.UserDao
import com.rijana.fitme.database.dao.UserProfileDao
import com.rijana.fitme.database.entity.User
import com.rijana.fitme.database.entity.UserProfile

@Database(
    entities = [
        User::class,
        UserProfile::class
    ],
    version = 3
)
abstract class FitMeDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun userProfileDao(): UserProfileDao
}