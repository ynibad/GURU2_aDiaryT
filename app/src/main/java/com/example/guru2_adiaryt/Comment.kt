package com.example.guru2_adiaryt

// 댓글 RecyclerView에 들어갈 Class 정의
data class Comment(
    val username: String,
    val timestamp: String,
    val content: String
)