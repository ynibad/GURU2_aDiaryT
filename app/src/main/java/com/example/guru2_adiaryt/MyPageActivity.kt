package com.example.guru2_adiaryt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView  // ListView를 import
import android.widget.Toast

class MyPageActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)

        dbHelper = DBHelper(this)

        // ID가 'listView'인 ListView를 찾기
        val listView: ListView = findViewById(R.id.listView)

        val item = arrayOf("내 정보 수정", "공지사항", "피드백")

        // ArrayAdapter를 사용하여 ListView에 데이터 채우기
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)

        // 리스트뷰의 아이템 클릭 리스너 설정
        listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) { // "내 정보 수정"을 눌렀을 때

                // SharedPreferences에서 사용자 이메일 정보 가져오기
                val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                val currentUserEmail = sharedPreferences.getString("email", null)

                if (currentUserEmail != null) {
                    // 사용자 이메일을 기반으로 사용자 정보 가져오기
                    val userInfo = dbHelper.getUserInfo(currentUserEmail)

                    if (userInfo != null) {
                        val intent = Intent(this, EditUserInfoActivity::class.java)
                        intent.putExtra("currentUserEmail", currentUserEmail)
                        startActivity(intent)
                    } else {
                        // 사용자 정보를 찾을 수 없음
                        Toast.makeText(this, "현재 로그인된 사용자 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // SharedPreferences에 사용자 이메일 정보가 없음
                    Toast.makeText(this, "현재 로그인된 사용자 정보가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            // "공지사항", "피드백"을 눌렀을 때
        }
    }
}
