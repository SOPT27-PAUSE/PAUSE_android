package com.example.pause_android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*
import java.util.*
import kotlin.concurrent.timer

class WebViewActivity : AppCompatActivity() {

    private var timerTask : Timer? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra("url")

        webview.settings.setSupportMultipleWindows(true)
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.allowFileAccess = true
        webview.settings.allowFileAccessFromFileURLs = true
        webview.settings.allowUniversalAccessFromFileURLs = true
        webview.loadUrl(url)
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = WebViewClient()

        startTimer()
    }

    private fun startTimer() {

        var minute = 0
        var second = 7

        timerTask = timer(period = 1000) {

            runOnUiThread {
                //var timer = String.format("%02d:%02d", minute, second)
                //tv_timer.text = timer
                Log.d("타이머", "$minute : $second")
            }

            if(second == 0 && minute == 0) {
                //타이머 종료
                runOnUiThread {
                    if(intent.getIntExtra("temp", -1) == 0) {
                        val intent = Intent(applicationContext, ListActivity::class.java)
                        intent.putExtra("time", "25")
                        setResult(RESULT_OK, intent)
                        finish()
                    } else if (intent.getIntExtra("temp", -1) == 1) {
                        val intent = Intent(applicationContext, FinishActivity::class.java)
                        startActivity(intent)
                    }
                }
                cancel()
            }
            if(second == 0) {
                minute--
                second = 60
            }
            second--
        }

    }
}