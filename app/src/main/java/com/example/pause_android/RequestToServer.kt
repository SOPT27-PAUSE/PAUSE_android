package com.example.pause_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://sopkathon-pause.tk")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}