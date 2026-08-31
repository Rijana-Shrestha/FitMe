package com.rijana.fitme.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val description: String? = null,

    val muscleGroup: String? = null,

    val equipment: String? = null,

    val imageUrl: String? = null
)