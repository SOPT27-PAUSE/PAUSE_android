package com.example.pause_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    val requestToServer = RequestToServer

    lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        requestToServer.service.requestPlaylist(
            playtime = 30,
            category = "예능"
        ).enqueue(
            object : Callback<ResponseListData> {
                override fun onResponse(
                    call: Call<ResponseListData>,
                    response: Response<ResponseListData>
                ) {
                    if(response.isSuccessful) {
                        Log.d("테스트", response.body()!!.data.toString())
                        listAdapter = ListAdapter(applicationContext, response.body()!!.data)
                        activity_list_rv_video.adapter = listAdapter
                        listAdapter.notifyDataSetChanged()

                        listAdapter.setItemClickListener(
                            object : ListAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    val intent = Intent(applicationContext, WebViewActivity::class.java)
                                    intent.putExtra("url", response.body()!!.data[position].url)
                                    startActivity(intent)
                                }

                            }
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseListData>, t: Throwable) {
                    Log.d("통신실패", "$t")
                }

            }
        )
    }
}