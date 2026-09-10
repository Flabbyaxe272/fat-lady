package com.farrisfam.fatflady.core.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

data class PlaybackState(
    val isPlaying: Boolean = false,
    val currentPositionMs: Long = 0L,
    val durationMs: Long = 0L,
    val songTitle: String = ""
)

@Singleton
class PlayerManager @Inject constructor(
    @ApplicationContext context: Context
) {
    val player = ExoPlayer.Builder(context).build()

    private val _state = MutableStateFlow(PlaybackState())
    val state: StateFlow<PlaybackState> = _state

    fun playUri(uri: String, title: String) {
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
        _state.value = _state.value.copy(isPlaying = true, songTitle = title)
    }

    fun togglePlayPause() {
        if (player.isPlaying) player.pause() else player.play()
        _state.value = _state.value.copy(isPlaying = player.isPlaying)
    }

    fun seekTo(positionMs: Long) = player.seekTo(positionMs)

    @Suppress("unused")
    fun release() = player.release()
}

@HiltViewModel
class PlayerManagerHolder @Inject constructor(
    val playerManager: PlayerManager
) : ViewModel()