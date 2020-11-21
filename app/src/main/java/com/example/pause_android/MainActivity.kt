package com.example.pause_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select_time.setOnClickListener{
            val time_popup= PopupMenu(this,select_time)

            menuInflater.inflate(R.menu.time_option,time_popup.menu)
            time_popup.setOnMenuItemClickListener(PopupListener())
            time_popup.show()
        }



        category.setOnClickListener{
            val category_popup= PopupMenu(this,category)

            menuInflater.inflate(R.menu.time_option,category_popup.menu)
            category_popup.setOnMenuItemClickListener(PopupListener())
            category_popup.show()
        }

        Button_record.setOnClickListener{

        }
    }

    inner class PopupListener: PopupMenu.OnMenuItemClickListener {

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId) {
                R.id.one_hr -> select_time.text ="1시간"
                R.id.one_half_hr -> select_time.text ="1시간 30분"
                R.id.two_hr -> select_time.text ="2시간"
                R.id.two_half_hr -> select_time.text ="2시간 30분"
                R.id.three_hr -> select_time.text ="3시간"
                R.id.eat -> select_time.text ="먹방"
                R.id.funny -> select_time.text ="예능"
                R.id.music -> select_time.text ="음악"
                R.id.drama -> select_time.text ="드라마"
                R.id.asmr -> select_time.text ="ASMR"
                R.id.fashion -> select_time.text ="패션"
                R.id.game -> select_time.text ="게임"
            }

            return false
        }
    }

}