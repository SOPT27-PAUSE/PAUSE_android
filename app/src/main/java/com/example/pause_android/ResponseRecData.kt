package com.example.pause_android

data class ResponseRecData(
    val `data`: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val date: Int,
        val setTime: Int,
        val useTime: Int
    )
}