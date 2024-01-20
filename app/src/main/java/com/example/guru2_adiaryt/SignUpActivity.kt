package com.example.guru2_adiaryt

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var regionSpinner: Spinner
    private lateinit var signUpButton: Button
    private lateinit var backToLoginButton: Button
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditTextSignUp)
        passwordEditText = findViewById(R.id.passwordEditTextSignUp)
        regionSpinner = findViewById(R.id.regionSpinner)
        signUpButton = findViewById(R.id.signUpButtonSignUp)
        backToLoginButton = findViewById(R.id.backToLoginButton)

        // Spinner에 표시될 서울 구 목록 데이터
        val seoulDistricts = arrayOf("<주거 지역 선택>", "강남구", "강동구", "강북구",
            "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구",
            "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구",
            "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구")

        // Spinner에 ArrayAdapter 연결
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seoulDistricts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regionSpinner.adapter = adapter


        dbHelper = DBHelper(this)

        // 회원가입 버튼 클릭 이벤트
        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Spinner에서 선택된 지역 가져오기
            val selectedDistrict = regionSpinner.selectedItem.toString()

            // <주거 지역 선택>이 선택된 경우 처리
            if (selectedDistrict == "[주거 지역 선택]") {
                Toast.makeText(this, "주거 지역을 선택하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // DBHelper를 사용하여 회원 정보 저장
            val userId = dbHelper.insertUser(username, email, password)

            if (userId != -1L) {
                // 회원가입 성공
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                // 회원가입 완료 후 로그인 화면으로 이동
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                // 회원가입 실패
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }

        // 로그인 화면으로 돌아가기
        backToLoginButton.setOnClickListener {
            // 로그인 화면으로 이동
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
