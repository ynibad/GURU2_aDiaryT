package com.example.guru2_adiaryt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_adiaryt.Article
import com.example.guru2_adiaryt.R

class FirstFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articlesList: List<Article>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        articlesList = getArticlesForTab()
        articleAdapter = ArticleAdapter(articlesList)
        recyclerView.adapter = articleAdapter
    }

    private fun getArticlesForTab(): List<Article> {
        return listOf(Article("Title 1", "Content 1"), Article("Title 2", "Content 2"))
    }
}