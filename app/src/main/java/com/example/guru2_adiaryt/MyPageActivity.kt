package com.example.guru2_adiaryt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView  // ListView를 import

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)

        // ID가 'listView'인 ListView를 찾기
        val listView: ListView = findViewById(R.id.listView)

        val item = arrayOf("내 정보 수정", "공지사항", "피드백")

        // ArrayAdapter를 사용하여 ListView에 데이터 채우기
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)
    }
}
