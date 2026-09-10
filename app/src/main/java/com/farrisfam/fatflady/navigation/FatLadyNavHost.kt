package com.farrisfam.fatflady.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farrisfam.fatflady.feature.home.HomeScreen
import com.farrisfam.fatflady.feature.nowplaying.NowPlayingScreen
import com.farrisfam.fatflady.feature.placeholder.PlaceholderScreen
import com.farrisfam.fatflady.feature.songs.SongsScreen
import com.farrisfam.fatflady.feature.songs.SongsViewModel

@Composable
fun FatLadyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = FatLadyDestination.Home.route, modifier = modifier) {
        composable(FatLadyDestination.Home.route) {
            HomeScreen(onNavigateToNowPlaying = {
                navController.navigate(FatLadyDestination.NowPlaying.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(FatLadyDestination.Albums.route) { PlaceholderScreen("Albums") }
        composable(FatLadyDestination.Artists.route) { PlaceholderScreen("Artists") }
        composable(FatLadyDestination.Songs.route) {
            val songsViewModel: SongsViewModel = hiltViewModel()
            SongsScreen(
                onSongClick = { song ->
                    songsViewModel.playSong(song)
                    navController.navigate(FatLadyDestination.NowPlaying.route) {
                        launchSingleTop = true
                    }
                },
                viewModel = songsViewModel
            )
        }
        composable(FatLadyDestination.Playlists.route) { PlaceholderScreen("Playlists") }
        composable(FatLadyDestination.Search.route) { PlaceholderScreen("Search") }
        composable(FatLadyDestination.Settings.route) { PlaceholderScreen("Settings") }
        composable(FatLadyDestination.NowPlaying.route) { NowPlayingScreen() }
    }
}