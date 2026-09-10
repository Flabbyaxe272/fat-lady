package com.farrisfam.fatflady.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.farrisfam.fatflady.core.player.PlayerManager
import com.farrisfam.fatflady.feature.player.MiniPlayerBar

@Composable
fun FatLadyScaffold(
    navController: NavHostController,
    playerManager: PlayerManager
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            androidx.compose.foundation.layout.Column {
                if (currentRoute != FatLadyDestination.NowPlaying.route) {
                    MiniPlayerBar(
                        playerManager = playerManager,
                        visible = currentRoute != FatLadyDestination.NowPlaying.route,
                        onBarClick = {
                            navController.navigate(FatLadyDestination.NowPlaying.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
                NavigationBar {
                    bottomNavDestinations.forEach { destination ->
                        NavigationBarItem(
                            selected = currentRoute == destination.route,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            },
                            icon = { Icon(destination.icon!!, contentDescription = destination.label) },
                            label = { Text(destination.label!!) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        FatLadyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}