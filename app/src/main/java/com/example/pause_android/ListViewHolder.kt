package com.example.pause_android

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.img_thumbnail)
    val title = itemView.findViewById<TextView>(R.id.tv_video_title)
    val count = itemView.findViewById<TextView>(R.id.tv_video_count)
    val playtime = itemView.findViewById<TextView>(R.id.tv_video_time)

    @SuppressLint("SetTextI18n")
    fun bind(videoData: VideoData) {
        Glide.with(itemView).load(videoData.image).into(image)
        title.text = videoData.title
        count.text = videoData.count.toString() + "개"
        playtime.text = videoData.playtime.toString() + ":00"
    }
}