package com.rijana.fitme.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rijana.fitme.database.entity.WorkoutLog

@Dao
interface WorkoutLogDao {

    @Insert
    suspend fun insertWorkoutLog(workoutLog: WorkoutLog): Long

    @Query("SELECT * FROM workout_logs WHERE userId = :userId ORDER BY completedAt DESC")
    suspend fun getLogsForUser(userId: Int): List<WorkoutLog>

    @Query("SELECT * FROM workout_logs WHERE workoutId = :workoutId ORDER BY completedAt DESC")
    suspend fun getLogsForWorkout(workoutId: Int): List<WorkoutLog>

    @Update
    suspend fun updateWorkoutLog(workoutLog: WorkoutLog)

    @Delete
    suspend fun deleteWorkoutLog(workoutLog: WorkoutLog)
}