package com.example.guru2_adiaryt

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_adiaryt.R

// ArticleAdapter 클래스-> RecyclerView에서 사용할 Article 객체의 어댑터
class ArticleAdapter(private val articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    // 각 아이템을 위한 ViewHolder 클래스
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.articleTitle)
        // 다른 View 초기화
    }

    // 새로운 ViewHolder 인스턴스를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    // 지정된 위치에 데이터를 ViewHolder의 뷰에 바인딩함
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.title

        // 각 Article 클릭 시 상세 페이지로 전환
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    // 전체 아이템의 수를 반환
    override fun getItemCount(): Int = articles.size
}
