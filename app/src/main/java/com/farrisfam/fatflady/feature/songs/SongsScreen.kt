package com.farrisfam.fatflady.feature.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.farrisfam.fatflady.data.model.Song
import io.iamjosephmj.flinger.flings.flingBehavior
import io.iamjosephmj.flinger.callbacks.FlingCallbacks
import io.iamjosephmj.flinger.configs.FlingConfiguration

@Composable
fun SongsScreen(
    onSongClick: (Song) -> Unit,
    viewModel: SongsViewModel = hiltViewModel()
) {
    val songs by viewModel.songs.collectAsState()
    val callbacks = FlingCallbacks(
        onFlingStart = { velocity -> android.util.Log.d("FlingDebug", "start velocity: $velocity") }
    )

    if (songs.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "No songs found on device",
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            // Fling Tuning - For polishing later
            flingBehavior = flingBehavior(
                scrollConfiguration = FlingConfiguration.Builder()
                    .scrollViewFriction(0.008f)
                    .decelerationFriction(0.12f)
                    .build()
            )
        ) {
            stickyHeader {
                Surface(tonalElevation = 2.dp) {
                    Text(
                        text = "Songs (${songs.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
            items(songs, key = { it.id }) { song ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSongClick(song) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = song.artist, style = MaterialTheme.typography.bodySmall)
                }
                HorizontalDivider()
            }
        }
    }
}