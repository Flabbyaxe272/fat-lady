package com.farrisfam.fatflady

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.farrisfam.fatflady.navigation.FatLadyScaffold
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.farrisfam.fatflady.core.player.PlayerManagerHolder


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        Manifest.permission.READ_MEDIA_AUDIO
                    } else {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    }

                    val launcher = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { /* result handled reactively by ViewModel re-query, see Step 6 */ }

                    LaunchedEffect(Unit) {
                        launcher.launch(permission)
                    }

                    val playerManagerHolder: PlayerManagerHolder = hiltViewModel()
                    val navController = rememberNavController()

                    FatLadyScaffold(
                        navController = navController,
                        playerManager = playerManagerHolder.playerManager
                    )
                }
            }
        }
    }
}