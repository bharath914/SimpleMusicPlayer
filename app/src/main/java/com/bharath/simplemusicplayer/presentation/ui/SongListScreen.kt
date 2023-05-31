package com.bharath.simplemusicplayer.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.simplemusicplayer.presentation.viewmodel.SongListViewModel

@Composable
fun SongListScreen(
    viewModel: SongListViewModel = hiltViewModel(),

    ) {
        val state=viewModel.state.value
        Box(

            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ){
            LazyColumn{
                items(state.list){song->
                    SongCard(song = song)

                }
            }

            if (state.message.isNotBlank()){

                Text(text = state.message ,
                     fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.error
                    , textAlign = TextAlign.Center

                    )

            }
            if (state.isLoading){
                CircularProgressIndicator()
            }


        }

}