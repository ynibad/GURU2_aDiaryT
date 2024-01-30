package com.example.guru2_adiaryt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Comment Adapter Class: RecyclerView에 댓글 데이터를 표시하기 위한 어댑터 클래스 생성
class CommentAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    // RecyclerView에서 각 댓글의 뷰를 담기 위한 Holder 생성
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

    // 각 뷰 홀더에 데이터를 바인딩하여 화면에 표시
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 댓글 리스트에서 현재 위치의 댓글 가져오기
        val comment = comments[position]
        // ViewHolder의 뷰에 댓글 데이터 설정
        holder.usernameTextView.text = comment.username
        holder.timestampTextView.text = comment.timestamp
        holder.contentTextView.text = comment.content
    }

    // 어댑터가 관리하는 데이터의 총 개수 반환
    override fun getItemCount(): Int {
        return comments.size
    }
}
