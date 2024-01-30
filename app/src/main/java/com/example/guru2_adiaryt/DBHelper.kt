package com.example.guru2_adiaryt

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // DB 관련 상수
    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }

    // DB 생성
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)"

        db?.execSQL(createTableQuery)
    }

    // DB 업그레이드
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // 사용자 정보 추가 메서드
    fun insertUser(username: String, email: String, password: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    // 로그인 메서드
    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_PASSWORD)
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor: Cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null)
        val loginSuccess = cursor.count > 0
        cursor.close()
        db.close()
        return loginSuccess
    }

    // 사용자 정보 가져오기
    @SuppressLint("Range")
    fun getUserInfo(email: String): User? {
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        var user: User? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            user = User(id, username, email, password)
        }
        cursor.close()
        db.close()
        return user
    }

    // 사용자 정보 수정
    fun updateUserInfo(id: Int, newUsername: String, newPassword: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, newUsername)
            put(COLUMN_PASSWORD, newPassword)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
        return rowsUpdated > 0
    }

    // user information
    data class User(val id: Int, val username: String, val email: String, val password: String)
}