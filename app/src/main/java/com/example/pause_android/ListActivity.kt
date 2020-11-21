package com.example.pause_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
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

        requestList("ASMR")

        btn_go_record.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }


        activity_list_rv_video.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY > oldScrollY) {
                val animation = TranslateAnimation(0F, 0F, 0F, 500F)
                animation.duration = 1000

                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        btn_stop.visibility = GONE
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        btn_stop.visibility = GONE
                    }
                    override fun onAnimationRepeat(animation: Animation?) { }
                })
                btn_stop.startAnimation(animation)
            }

            if(scrollY <oldScrollY) {
                val topani = TranslateAnimation(0F, 0F, 500F, 0F)
                topani.duration=1000

                topani.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        btn_stop.visibility = VISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {

                    }

                    override fun onAnimationRepeat(animation: Animation?) { }
                })
                btn_stop.startAnimation(topani)
            }
        }





        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(spinner.getItemAtPosition(position)) {
                    "먹방" -> {
                        activity_list_tv_category.text = "먹방"
                    }
                    "예능" -> {
                        activity_list_tv_category.text = "예능"
                    }
                    "음악" -> {
                        activity_list_tv_category.text = "음악"
                        requestList("음악")
                    }
                    "드라마" -> {
                        activity_list_tv_category.text = "드라마"
                    }
                    "ASMR" -> {
                        activity_list_tv_category.text = "ASMR"
                        requestList("ASMR")
                    }
                    "패션" -> {
                        activity_list_tv_category.text = "패션"
                    }
                    "게임" -> {
                        activity_list_tv_category.text = "게임"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    private fun requestList(category : String) {
        requestToServer.service.requestPlaylist(
            playtime = 30,
            category = category
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