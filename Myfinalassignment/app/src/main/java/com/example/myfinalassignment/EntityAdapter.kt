package com.example.myfinalassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for displaying a list of entities in a RecyclerView
class EntityAdapter(
    private val entityList: List<Entity>,  // List of entities to display
    private val onItemClick: (Entity) -> Unit  // Lambda function for handling item clicks
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    // ViewHolder class that holds references to the views for each item
    class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProperty1: TextView = view.findViewById(R.id.tvProperty1)
        val tvProperty2: TextView = view.findViewById(R.id.tvProperty2)
    }

    // Inflates the item view and creates a ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    // Binds data (entity) to the ViewHolder
    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entityList[position]
        holder.tvProperty1.text = entity.property1
        holder.tvProperty2.text = entity.property2

        // Handle item click by invoking the provided lambda
        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int = entityList.size
}
