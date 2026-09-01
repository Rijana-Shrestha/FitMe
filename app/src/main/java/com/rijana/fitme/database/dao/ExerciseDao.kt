package com.rijana.fitme.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rijana.fitme.database.entity.Exercise

@Dao
interface ExerciseDao {

    @Insert
    suspend fun insertExercise(exercise: Exercise): Long

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercises(): List<Exercise>

    @Query("SELECT * FROM exercises WHERE muscleGroup = :muscleGroup")
    suspend fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise>

    /**
     * One representative exercise per muscle group (lowest id in each group).
     * Used to power the "Most Popular Workouts" row on Discover until a real
     * Workout entity with actual popularity/usage data exists.
     */
    @Query("SELECT * FROM exercises GROUP BY muscleGroup")
    suspend fun getOneExercisePerMuscleGroup(): List<Exercise>

    @Query("SELECT COUNT(*) FROM exercises")
    suspend fun getExerciseCount(): Int

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}