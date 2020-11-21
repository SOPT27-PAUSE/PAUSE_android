package com.example.pause_android

import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    // 플레이리스트 조회
    @Headers("Content-Type: application/json")
    @GET("/playlist")
    fun requestPlaylist(
        @Query("playtime") playtime : Int,
        @Query("category") category : String
    ) : Call<ResponseListData>

    @Headers("Content-Type: application/json")
    @POST("/auth/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
    @GET("/usage")
    fun returnTime(
        @Header("jwt") jwt : String
    ) : Call<ResponseRecData>

}