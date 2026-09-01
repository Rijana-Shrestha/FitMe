package com.rijana.fitme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rijana.fitme.R
import com.rijana.fitme.database.DatabaseProvider
import kotlinx.coroutines.launch

class DiscoverFragment : Fragment() {

    private val database by lazy {
        DatabaseProvider.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_discover,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews(view)
        loadExercises()
    }

    private fun setupRecyclerViews(view: View) {

        val popularRecyclerView =
            view.findViewById<androidx.recyclerview.widget.RecyclerView>(
                R.id.rv_popular_workouts
            )

        val quickRecyclerView =
            view.findViewById<androidx.recyclerview.widget.RecyclerView>(
                R.id.rv_quick_workouts
            )

        val categoriesRecyclerView =
            view.findViewById<androidx.recyclerview.widget.RecyclerView>(
                R.id.rv_categories
            )

        popularRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        quickRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        categoriesRecyclerView.layoutManager =
            GridLayoutManager(
                requireContext(),
                4
            )
    }

    private fun loadExercises() {

        viewLifecycleOwner.lifecycleScope.launch {

            val exercises =
                database.exerciseDao().getAllExercises()

            val categories = exercises
                .mapNotNull { it.muscleGroup }
                .distinct()

            android.util.Log.d(
                "FITME_DISCOVER",
                "Exercises: ${exercises.size}, Categories: $categories"
            )

            val categoriesRecyclerView =
                requireView().findViewById<
                        androidx.recyclerview.widget.RecyclerView
                        >(R.id.rv_categories)

            categoriesRecyclerView.adapter =
                CategoryAdapter(categories)

            // Most Popular Workouts: one representative exercise per muscle
            // group. There's no real Workout/popularity data yet, so this
            // is a stand-in until a proper Workout entity exists.
            val popularExercises =
                database.exerciseDao().getOneExercisePerMuscleGroup()

            android.util.Log.d(
                "FITME_DISCOVER",
                "Most popular: ${popularExercises.map { it.name }}"
            )

            val popularRecyclerView =
                requireView().findViewById<
                        androidx.recyclerview.widget.RecyclerView
                        >(R.id.rv_popular_workouts)

            popularRecyclerView.adapter =
                WorkoutCardAdapter(popularExercises)
        }
    }
}