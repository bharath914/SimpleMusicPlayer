package com.bharath.simplemusicplayer.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.simplemusicplayer.common.Resource
import com.bharath.simplemusicplayer.presentation.state.SongListState
import com.bharath.simplemusicplayer.usecases.SongsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val songUseCase:SongsListUseCase
):ViewModel()
{
    private var _state= mutableStateOf(SongListState())
    var state:State<SongListState> =_state
    private var isLoaded = mutableStateOf(false)

    init {
        getSongs()
    }

    fun getSongs(){
        songUseCase().onEach {result->
            when(result){
                is Resource.Success ->{
                    _state.value= SongListState(list = result.data ?: mutableListOf())
                    isLoaded.value=true

                }
                is Resource.Loading ->{
                    _state.value=SongListState(isLoading = true)
                }
                is Resource.Error ->{
                _state.value=SongListState(message = result.message ?: "Unexpected Error")
                }
            }

        }.launchIn(viewModelScope)
    }

}