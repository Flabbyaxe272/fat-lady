package com.farrisfam.fatflady.data.repository

import com.farrisfam.fatflady.data.local.MediaStoreSongSource
import com.farrisfam.fatflady.data.model.Song
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
    private val mediaStoreSongSource: MediaStoreSongSource
) {
    fun getSongs(): List<Song> = mediaStoreSongSource.querySongs()
}