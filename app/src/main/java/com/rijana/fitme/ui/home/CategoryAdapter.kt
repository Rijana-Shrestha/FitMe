package com.rijana.fitme.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rijana.fitme.R

class CategoryAdapter(
    private val categories: List<String>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val tvCategory: TextView =
            itemView.findViewById(R.id.tvCategory)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {

        Log.d(
            "FITME_CATEGORY",
            "onCreateViewHolder called"
        )

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_category,
                parent,
                false
            )

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {

        Log.d(
            "FITME_CATEGORY",
            "Binding category: ${categories[position]}"
        )

        holder.tvCategory.text = categories[position]
    }

    override fun getItemCount(): Int {
        Log.d(
            "FITME_CATEGORY",
            "getItemCount: ${categories.size}"
        )

        return categories.size
    }
}