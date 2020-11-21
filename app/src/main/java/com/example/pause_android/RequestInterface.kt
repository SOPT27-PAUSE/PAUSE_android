package com.example.pause_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RequestInterface {

    // 플레이리스트 조회
    @Headers("Content-Type: application/json")
    @GET("/playlist")
    fun requestPlaylist(
        @Query("playtime") playtime : Int,
        @Query("category") category : String
    ) : Call<ResponseListData>

}