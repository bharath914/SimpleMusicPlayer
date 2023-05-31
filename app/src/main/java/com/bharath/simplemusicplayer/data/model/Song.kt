package com.bharath.simplemusicplayer.data.model

import android.net.Uri

data class Song(
    val id:Long,
    val title:String,
    val artist:String,
    val albumart:Uri,
    val songUri: Uri,
    val duration:Long,
    val albumName:String
)
