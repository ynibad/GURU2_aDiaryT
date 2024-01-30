package com.example.guru2_adiaryt

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class EditUserInfoActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_userinfo)

        dbHelper = DBHelper(this)
        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val saveButton: Button = findViewById(R.id.saveButton)
        val cancelButton: Button = findViewById(R.id.cancleButton)

        cancelButton.setOnClickListener {
            finish() // 현재 액티비티 종료
        }

        saveButton.setOnClickListener {
            // 사용자가 입력한 정보 가져오기
            val newUsername = findViewById<EditText>(R.id.nicknameEditText).text.toString()
            val newPassword = findViewById<EditText>(R.id.passwordEditTextEditProfile).text.toString()

            // 현재 로그인된 사용자의 이메일 정보 가져오기
            val email = sharedPreferences.getString("email", "")

            // 현재 로그인된 사용자의 정보 가져오기
            val currentUser = dbHelper.getUserInfo(email!!) // 이메일에 해당하는 사용자 정보 가져오기

            // 사용자 정보 업데이트
            if (currentUser != null) {
                val isUpdated = dbHelper.updateUserInfo(currentUser.id, newUsername, newPassword)
                if (isUpdated) {
                    // 업데이트 성공
                    Toast.makeText(this, "회원 정보가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show()
                    finish() // 화면 닫기
                } else {
                    // 업데이트 실패
                    Toast.makeText(this, "회원 정보 업데이트에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // 사용자 정보를 찾을 수 없음
                Toast.makeText(this, "현재 로그인된 사용자 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
