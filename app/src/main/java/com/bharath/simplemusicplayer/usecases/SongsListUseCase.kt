package com.bharath.simplemusicplayer.usecases

import com.bharath.simplemusicplayer.common.Resource
import com.bharath.simplemusicplayer.data.model.Song
import com.bharath.simplemusicplayer.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SongsListUseCase @Inject constructor(private val repo:Repository) {
    operator fun invoke():Flow<Resource<MutableList<Song>>> = flow {

        try {
            emit(Resource.Loading())
            val songs= repo.getAllAudio()
            emit(Resource.Success(data = songs))

        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }

    }
}