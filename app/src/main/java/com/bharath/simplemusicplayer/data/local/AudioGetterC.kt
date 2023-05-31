package com.bharath.simplemusicplayer.data.local

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.bharath.simplemusicplayer.data.model.Song
import com.bharath.simplemusicplayer.domain.AudioGet
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioGetterC @Inject constructor (@ApplicationContext val context: Context) : AudioGet {
    override suspend fun getAllAudio(): MutableList<Song> {
        val list: MutableList<Song> = ArrayList()

        val mediaUri: Uri =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ARTIST,
            MediaStore.Audio.AudioColumns.ALBUM_ID,
            MediaStore.Audio.AudioColumns.DURATION,
            MediaStore.Audio.AudioColumns.ALBUM,


            )
        val sortOrder = MediaStore.Audio.AudioColumns.DATE_MODIFIED + " DESC"

        val selection = MediaStore.Audio.AudioColumns.IS_MUSIC

        context.contentResolver.query(mediaUri, projection, selection, null, sortOrder)
            .use { cursor ->

                val idColumn = cursor!!.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)
                val artistColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
                val albumIdColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID)
                val durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)
                val albumNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)

                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getLong(idColumn)
                        val title = cursor.getString(titleColumn)
                        val artist = cursor.getString(artistColumn)
                        val albumId = cursor.getLong(albumIdColumn)
                        val albumName = cursor.getString(albumNameColumn)
                        val duration = cursor.getLong(durationColumn)

                        val songUri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                            id
                        )
                        val albumArt = ContentUris.withAppendedId(
                            Uri.parse(
                                "content://media/external/audio/albumart"
                            ), albumId
                        )

                        list.add(Song(id, title, artist, albumArt, songUri, duration, albumName))


                    } while (cursor.moveToNext())
                }
                cursor.close()









            }





        return list
    }
}