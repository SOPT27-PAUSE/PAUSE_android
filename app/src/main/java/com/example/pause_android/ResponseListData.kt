package com.example.pause_android

data class ResponseListData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : MutableList<VideoData>
)

data class VideoData(
    val id : Int,
    val url : String,
    val category : String,
    val image : String,
    val title : String,
    val playtime : Int,
    val count : Int
)