package com.wcc.whatdoilearn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wcc.whatdoilearn.R
import com.wcc.whatdoilearn.entities.LearnedItem
import com.wcc.whatdoilearn.view.LearnedItemsAdapter.LearnedItemViewHolder

class LearnedItemsAdapter: RecyclerView.Adapter<LearnedItemViewHolder>() {
    var data = listOf<LearnedItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.learned_item, parent, false) as View
        return LearnedItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LearnedItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item.tittle, item.description, item.understandingLevel.color)
    }

    inner class LearnedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val itemTitle = itemView.findViewById<TextView>(R.id.learningItemTitle)
        private val itemDescription = itemView.findViewById<TextView>(R.id.learningItemDescription)
        private val itemUnderstadingContainer = itemView.findViewById<View>(R.id.understadingLevelContainer)

        fun bind(title: String, description: String, color: Int) {
            itemTitle.text = title
            itemDescription.text = description
            itemUnderstadingContainer.setBackgroundResource(color)
        }
    }
}


