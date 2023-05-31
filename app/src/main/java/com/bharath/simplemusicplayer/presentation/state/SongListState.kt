package com.bharath.simplemusicplayer.presentation.state

import com.bharath.simplemusicplayer.data.model.Song

data class SongListState (
    val isLoading:Boolean =false,
    val list: MutableList<Song> = mutableListOf(),
    val message:String = ""

        )
