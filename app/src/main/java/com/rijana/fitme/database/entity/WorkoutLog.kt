package com.rijana.fitme.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_logs",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("userId"),
        Index("workoutId"),
        Index("exerciseId")
    ]
)
data class WorkoutLog(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,

    val workoutId: Int,

    val exerciseId: Int,

    val setsCompleted: Int,

    val repsCompleted: Int,

    val weightUsed: Double? = null,

    val completedAt: Long = System.currentTimeMillis()
)