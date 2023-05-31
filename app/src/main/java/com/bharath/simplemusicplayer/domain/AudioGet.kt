package com.bharath.simplemusicplayer.domain

import com.bharath.simplemusicplayer.data.model.Song

interface AudioGet {
    suspend fun getAllAudio():MutableList<Song>
}