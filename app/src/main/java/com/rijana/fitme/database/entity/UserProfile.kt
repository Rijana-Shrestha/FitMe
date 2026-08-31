package com.rijana.fitme.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_profile",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserProfile(

    @PrimaryKey
    val userId: Int,

    val gender: String,

    val heightFeet: Int,

    val heightInches: Int,

    val weight: Double,

    val goal: String
)