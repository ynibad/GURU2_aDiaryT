package com.example.guru2_adiaryt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_adiaryt.R

// 다섯 번째 탭의 프래그먼트를 위한 클래스
class FifthFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articlesList: List<Article>

    // 뷰가 생성될 때 호출
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    // 뷰가 완전히 생성된 후 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        articlesList = getArticlesForTab() // 탭에 표시할 글들을 가져오기
        articleAdapter = ArticleAdapter(articlesList) // 어댑터에 글 목록 설정
        recyclerView.adapter = articleAdapter
    }

    // 탭에 표시할 글 목록을 가져오는 함수
    private fun getArticlesForTab(): List<Article> {
        return listOf(Article("Title 1", "Content 1"), Article("Title 2", "Content 2"),Article("Title 3", "Content 3"),
            Article("Title 4", "Content 4"),Article("Title 5", "Content 5"),
            Article("Title 6", "Content 6"),Article("Title 7", "Content 7"))
    }
}
