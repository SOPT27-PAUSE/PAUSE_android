package com.example.pause_android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.DataSet
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

                    fun setTimeData() : ArrayList<BarEntry> {
                        val entries = ArrayList<BarEntry>()
                        for(i in 0..6) {
                            entries.add(BarEntry(i.toFloat(),response.body()!!.data[i].setTime.toFloat()))
                        }
                        return entries
                    }

                    fun useTimeData() : ArrayList<BarEntry> {
                        val entries = ArrayList<BarEntry>()
                        for(i in 0..6) {
                            entries.add(BarEntry(i.toFloat(),response.body()!!.data[i].useTime.toFloat()))

                        }
                        return entries
                    }

                    val setTimebar = BarDataSet(setTimeData(),"")
                    val useTimebar= BarDataSet(useTimeData(),"")

                    setTimebar.setColor(Color.parseColor("#E2E2E2"))
                    useTimebar.setColor(Color.parseColor("#80EBE4"))
                    setTimebar.barBorderWidth

                    val barData = BarData()
                    barData.addDataSet(setTimebar)
                    barData.addDataSet(useTimebar)

                    graph_time.data=barData
                    graph_time.invalidate()
                    graph_time.apply {
                        description.isEnabled = false
                        setDrawGridBackground(false)
                        setDrawBarShadow(false)
                        setDrawBorders(false)
                        legend.isEnabled = false
                        xAxis.apply {
                            setDrawGridLines(false)
                            setDrawAxisLine(false)
                            disableGridDashedLine()
                        }
                        axisLeft.apply {
                            setDrawGridLines(false)
                            setDrawAxisLine(false)
                            isEnabled=false
                            setDrawLabels(false)
                        }
                        axisRight.apply {
                            setDrawGridLines(false)
                            setDrawAxisLine(false)
                            isEnabled=false
                            setDrawLabels(false)
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ResponseRecData>, t: Throwable) {
            }

        })


    }
}