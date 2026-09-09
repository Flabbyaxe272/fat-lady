package com.farrisfam.fatflady.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class FatLadyDestination(
    val route: String,
    val label: String? = null,
    val icon: ImageVector? = null
) {
    object Home : FatLadyDestination("home", "Home", Icons.Filled.Home)
    object Albums : FatLadyDestination("albums", "Albums", Icons.Filled.Album)
    object Artists : FatLadyDestination("artists", "Artists", Icons.Filled.Person)
    object Songs : FatLadyDestination("songs", "Songs", Icons.Filled.LibraryMusic)
    object Playlists : FatLadyDestination("playlists", "Playlists",
        Icons.AutoMirrored.Filled.PlaylistPlay
    )
    object Search : FatLadyDestination("search", "Search", Icons.Filled.Search)
    object Settings : FatLadyDestination("settings", "Settings", Icons.Filled.Settings)
    object NowPlaying : FatLadyDestination("now_playing")
}

val bottomNavDestinations = listOf(
    FatLadyDestination.Home,
    FatLadyDestination.Albums,
    FatLadyDestination.Artists,
    FatLadyDestination.Songs,
    FatLadyDestination.Search
)