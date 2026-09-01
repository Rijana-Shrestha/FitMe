package com.rijana.fitme.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rijana.fitme.R
import com.rijana.fitme.database.entity.Exercise

/**
 * Binds a horizontal list of [Exercise] rows into the existing
 * item_workout_card.xml design (image + badge + rating + title + author).
 *
 * Reused for both "Most Popular Workouts" and, later, "Quick Workouts" -
 * the two rows only differ in which list of exercises they're handed.
 */
class WorkoutCardAdapter(
    private val exercises: List<Exercise>,
    private val onItemClick: (Exercise) -> Unit = {}
) : RecyclerView.Adapter<WorkoutCardAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_workout_image)
        val tvBadge: TextView = itemView.findViewById(R.id.tv_badge)
        val layoutRating: View = itemView.findViewById(R.id.layout_rating)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_workout_title)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_workout_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_card, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val exercise = exercises[position]

        holder.tvTitle.text = exercise.name

        // "Author" line repurposed to show muscle group + equipment context,
        // since we don't have a real workout author/trainer yet.
        holder.tvAuthor.text = listOfNotNull(exercise.muscleGroup, exercise.equipment)
            .joinToString(" · ")

        // No real badge/rating data on Exercise yet - keep the card visually
        // consistent with its design rather than showing placeholder junk.
        holder.tvBadge.visibility = View.GONE
        holder.layoutRating.visibility = View.GONE

        holder.ivImage.setImageResource(R.drawable.placeholder_workout)

        holder.itemView.setOnClickListener { onItemClick(exercise) }
    }

    override fun getItemCount(): Int = exercises.size
}