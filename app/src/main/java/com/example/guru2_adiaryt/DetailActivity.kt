package com.example.guru2_adiaryt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var commentAdapter: CommentAdapter
    private val commentList = mutableListOf<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail)

        // RecyclerView 초기화
        val recyclerViewComments: RecyclerView = findViewById(R.id.recyclerViewComments)
        commentAdapter = CommentAdapter(commentList)
        recyclerViewComments.adapter = commentAdapter
        recyclerViewComments.layoutManager = LinearLayoutManager(this)

        // 댓글 전송 버튼 클릭 시
        val buttonPostComment: Button = findViewById(R.id.buttonPostComment)
        buttonPostComment.setOnClickListener {
            addComment()
        }
    }

    private fun addComment() {
        // 댓글 입력창에서 내용 가져오기
        val editTextComment: EditText = findViewById(R.id.editTextComment)
        val commentContent = editTextComment.text.toString()

        if (commentContent.isNotEmpty()) {
            // 현재 시간을 timestamp로 변환
            val timestamp = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date())

            // 새로운 댓글 생성 및 목록에 추가
            val newComment = Comment("사용자", timestamp, commentContent)
            commentList.add(newComment)

            // 어댑터에 변경 사항 알림
            commentAdapter.notifyDataSetChanged()

            // 댓글 입력창 초기화
            editTextComment.text.clear()
        }
    }
}
