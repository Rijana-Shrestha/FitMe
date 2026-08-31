package com.rijana.fitme.database

import com.rijana.fitme.database.entity.Exercise

object ExerciseSeedData {

    val exercises = listOf(

        // =========================
        // CHEST
        // =========================

        Exercise(
            name = "Push Up",
            description = "A bodyweight exercise targeting the chest, shoulders and triceps.",
            muscleGroup = "Chest",
            equipment = "None",
            imageUrl = null
        ),

        Exercise(
            name = "Bench Press",
            description = "A compound exercise targeting the chest, shoulders and triceps.",
            muscleGroup = "Chest",
            equipment = "Barbell",
            imageUrl = null
        ),

        Exercise(
            name = "Incline Dumbbell Press",
            description = "A chest exercise emphasizing the upper chest.",
            muscleGroup = "Chest",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        // =========================
        // BACK
        // =========================

        Exercise(
            name = "Lat Pulldown",
            description = "An exercise targeting the latissimus dorsi and upper back.",
            muscleGroup = "Back",
            equipment = "Cable Machine",
            imageUrl = null
        ),

        Exercise(
            name = "Pull Up",
            description = "A bodyweight exercise targeting the back and biceps.",
            muscleGroup = "Back",
            equipment = "Pull Up Bar",
            imageUrl = null
        ),

        Exercise(
            name = "Dumbbell Row",
            description = "An exercise targeting the upper back and lats.",
            muscleGroup = "Back",
            equipment = "Dumbbell",
            imageUrl = null
        ),

        // =========================
        // LEGS
        // =========================

        Exercise(
            name = "Squat",
            description = "A compound lower-body exercise targeting the quadriceps and glutes.",
            muscleGroup = "Legs",
            equipment = "Barbell",
            imageUrl = null
        ),

        Exercise(
            name = "Lunges",
            description = "A lower-body exercise targeting the quadriceps and glutes.",
            muscleGroup = "Legs",
            equipment = "None",
            imageUrl = null
        ),

        Exercise(
            name = "Leg Press",
            description = "A lower-body exercise targeting the quadriceps, hamstrings and glutes.",
            muscleGroup = "Legs",
            equipment = "Leg Press Machine",
            imageUrl = null
        ),

        Exercise(
            name = "Leg Extension",
            description = "An isolation exercise targeting the quadriceps.",
            muscleGroup = "Legs",
            equipment = "Leg Extension Machine",
            imageUrl = null
        ),

        // =========================
        // SHOULDERS
        // =========================

        Exercise(
            name = "Shoulder Press",
            description = "An exercise targeting the shoulders and triceps.",
            muscleGroup = "Shoulders",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        Exercise(
            name = "Lateral Raise",
            description = "An isolation exercise targeting the side deltoids.",
            muscleGroup = "Shoulders",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        Exercise(
            name = "Front Raise",
            description = "An exercise targeting the front deltoids.",
            muscleGroup = "Shoulders",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        // =========================
        // ARMS
        // =========================

        Exercise(
            name = "Bicep Curl",
            description = "An isolation exercise targeting the biceps.",
            muscleGroup = "Arms",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        Exercise(
            name = "Hammer Curl",
            description = "An exercise targeting the biceps and forearms.",
            muscleGroup = "Arms",
            equipment = "Dumbbells",
            imageUrl = null
        ),

        Exercise(
            name = "Tricep Extension",
            description = "An isolation exercise targeting the triceps.",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            imageUrl = null
        )
    )
}