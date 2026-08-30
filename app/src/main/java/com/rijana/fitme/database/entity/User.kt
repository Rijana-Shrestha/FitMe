package com.rijana.fitme.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val firebaseUid: String,

    val name: String,

    val email: String,

    val profileImage: String? = null,

    val bio: String? = null,

    val createdAt: Long = System.currentTimeMillis()
)