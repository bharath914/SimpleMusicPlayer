package com.bharath.simplemusicplayer.domain.repository

import com.bharath.simplemusicplayer.data.model.Song

interface Repository  {

    suspend fun getAllAudio():MutableList<Song>
}