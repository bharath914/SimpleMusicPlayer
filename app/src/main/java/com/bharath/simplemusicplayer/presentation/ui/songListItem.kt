package com.bharath.simplemusicplayer.presentation.ui

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bharath.simplemusicplayer.R
import com.bharath.simplemusicplayer.data.model.Song
import com.bharath.simplemusicplayer.ui.theme.cardBackgrounWhite
import com.bharath.simplemusicplayer.ui.theme.cardBackgroundBlack

@Composable
fun SongCard(
    song: Song,
) {
    val cardbackground = remember{ mutableStateOf( cardBackgrounWhite)}
    if (isSystemInDarkTheme()){
        cardbackground.value= cardBackgroundBlack
    }

    val size = (70 * Resources.getSystem().displayMetrics.densityDpi)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .background(cardbackground.value),
        shape = RoundedCornerShape(5.dp),



    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()

                .padding(start = 4.dp, end = 10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(song.albumart)
                        .error(R.drawable.sampleartwork)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.DISABLED)
                        .size(size, size)
                        .build(),
                    contentDescription = "Artwork"
                )
            }
            Box(modifier = Modifier.weight(5f)) {

                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.weight(2f),
                        text = song.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                        )
                    Text(
                        modifier = Modifier.weight(2f),
                        text = song.artist,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                }


            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
//    SongCard()
    }
}