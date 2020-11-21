package com.example.pause_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Request
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val requestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener{
            if(user_id.text.isNullOrBlank() || user_pw.text.isNullOrBlank()) { //빈칸이 있을 경우
                Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else { //처음 로그인
                Log.d("버튼", "버튼클릭")
                val id=user_id.text.toString()
                val password=user_pw.text.toString()


                requestToServer.service.postLogin(
                    RequestLoginData(
                        loginId = id,
                        password = password
                    )
                )
                    .enqueue(
                        object : Callback<ResponseLoginData> {
                            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                                Log.d("통신 실패", t.toString())
                            }

                            override fun onResponse(
                                call: Call<ResponseLoginData>,
                                response: Response<ResponseLoginData>
                            ) {
                                if (response.isSuccessful) {
                                    Log.d("login", response.body().toString())
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                        }
                    )}

        }
    }
}