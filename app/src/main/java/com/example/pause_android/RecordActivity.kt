package com.example.pause_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import kotlinx.android.synthetic.main.activity_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordActivity : AppCompatActivity() {
    val requestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        requestToServer.service.returnTime(
            RequestRecData(
                date = 22,
                setTime = 100,
                useTime = 90
            )
        ).enqueue(object : Callback<ResponseRecData>{
            override fun onResponse(
                call: Call<ResponseRecData>,
                response: Response<ResponseRecData>
            ) {
                if (response.isSuccessful) {
                    day7.text = response.body()!!.data[0].date.toString()
                }
            }

            override fun onFailure(call: Call<ResponseRecData>, t: Throwable) {

            }

        })
//        val NoOfEmp = ArrayList<Int>()
//
//        NoOfEmp.run {
//            add(0,10)
//            add(1,13)
//            add(2,20)
//        }
//
//        val year = ArrayList<Int>()
//
//        year.run {
//            add(16)
//            add(17)
//            add(18)
//        }
//        val barDataSet = BarDataSet(NoOfEmp,"bo")
//        graph_time.animateY(180)
//        val data = BarData(year, barDataSet)
//        barDataSet.colors(#80EBE4)
//        graph_time.data(data)
    }
}