package com.example.guru2_adiaryt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.textViewUsername)
        val timestampTextView: TextView = itemView.findViewById(R.id.textViewTimestamp)
        val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.usernameTextView.text = comment.username
        holder.timestampTextView.text = comment.timestamp
        holder.contentTextView.text = comment.content
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}
