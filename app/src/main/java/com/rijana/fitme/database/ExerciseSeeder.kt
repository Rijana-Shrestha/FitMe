package com.rijana.fitme.database

import com.rijana.fitme.database.dao.ExerciseDao

object ExerciseSeeder {

    suspend fun seedExercises(exerciseDao: ExerciseDao) {

        // Check whether exercises already exist
        val exerciseCount = exerciseDao.getExerciseCount()

        // Only seed when the table is empty
        if (exerciseCount == 0) {

            ExerciseSeedData.exercises.forEach { exercise ->

                exerciseDao.insertExercise(exercise)
            }
        }
    }
}