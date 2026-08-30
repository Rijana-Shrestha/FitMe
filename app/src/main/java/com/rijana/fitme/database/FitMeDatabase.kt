package com.rijana.fitme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rijana.fitme.database.dao.UserDao
import com.rijana.fitme.database.entity.User

@Database(
    entities = [User::class],
    version = 2
)
abstract class FitMeDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}