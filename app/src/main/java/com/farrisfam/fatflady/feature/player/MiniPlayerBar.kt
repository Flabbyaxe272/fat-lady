package com.farrisfam.fatflady.feature.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farrisfam.fatflady.core.player.PlayerManager

@Composable
fun MiniPlayerBar(
    playerManager: PlayerManager,
    visible: Boolean,
    onBarClick: () -> Unit
) {
    val state by playerManager.state.collectAsState()
    val shouldShow = visible && state.songTitle.isNotEmpty()

    AnimatedVisibility(
        visible = shouldShow,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }
    ) {
        Surface(tonalElevation = 3.dp) {
            Column {
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBarClick() }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.songTitle,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { playerManager.togglePlayPause() }) {
                        Icon(
                            imageVector = if (state.isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (state.isPlaying) "Pause" else "Play"
                        )
                    }
                }
            }
        }
    }
}