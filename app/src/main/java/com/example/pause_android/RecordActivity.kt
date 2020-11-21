package com.example.pause_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordActivity : AppCompatActivity() {
    val requestToServer = RequestToServer
    private var totalSaveTime : Int = 0
    private var totalWatchTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        requestToServer.service.returnTime(
            jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IuyYge2YhCIsImlhdCI6MTYwNTk5MTA4MSwiZXhwIjoxNjA3MjAwNjgxLCJpc3MiOiJ5c3MifQ.GeAtWJU65T8lpVPhr74vDAQuYVJxjrmR5HIGoyWIEH4"
        ).enqueue(object : Callback<ResponseRecData>{
            override fun onResponse(
                call: Call<ResponseRecData>,
                response: Response<ResponseRecData>
            ) {
                if (response.isSuccessful) {
                    for(i in 0..6) {
                        val resId = resources.getIdentifier(
                            "@id/day${7-i}",
                            "id",
                            applicationContext!!.packageName
                        )
                        val tv = this@RecordActivity.findViewById(resId) as TextView?
                        tv?.text = response.body()!!.data[i].date.toString()
                    }

                    for (i in 0..6) {
                        totalSaveTime = response.body()!!.data[i].setTime-response.body()!!.data[i].useTime
                    }
                    textview_minute.text = " " + totalSaveTime.toString() + "분"

                    for (i in 0..6) {
                        totalWatchTime += response.body()!!.data[i].setTime
                    }
                    val avgWatchTime : Int = totalWatchTime/7
                    textview_time.text = avgWatchTime.toString() + "분"
                    textview_avgtime.text = avgWatchTime.toString() + "분"
                }
            }

            override fun onFailure(call: Call<ResponseRecData>, t: Throwable) {
            }

        })
//        val NoOfEmp : ArrayList<Int>()
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