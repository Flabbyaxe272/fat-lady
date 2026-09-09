package com.farrisfam.fatflady.feature.nowplaying

import androidx.lifecycle.ViewModel
import com.farrisfam.fatflady.core.player.PlayerManager
import com.farrisfam.fatflady.core.player.PlaybackState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val playerManager: PlayerManager
) : ViewModel() {

    val playbackState: StateFlow<PlaybackState> = playerManager.state

    fun togglePlayPause() = playerManager.togglePlayPause()
    fun seekTo(positionMs: Long) = playerManager.seekTo(positionMs)
    fun playTestSong() {
        playerManager.playUri("android.resource://com.farrisfam.fatflady/raw/test_song", "Test Song")
    }
}

