package com.bharath.simplemusicplayer.data.repository

import com.bharath.simplemusicplayer.data.local.AudioGetterC
import com.bharath.simplemusicplayer.data.model.Song
import com.bharath.simplemusicplayer.domain.repository.Repository
import javax.inject.Inject

class RepoImpl @Inject constructor(private val audio:AudioGetterC) :Repository {
    override suspend fun getAllAudio(): MutableList<Song> {
        return audio.getAllAudio()
    }

}