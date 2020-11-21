package com.example.pause_android

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val accessToken: String,
        val refreshToken: String,
    )
}