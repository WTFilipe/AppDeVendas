package com.filipeoliveira.appdevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filipeoliveira.appdevendas.ui.components.SalesBottomNavigation
import com.filipeoliveira.appdevendas.ui.screens.home.HomeScreen
import com.filipeoliveira.appdevendas.ui.screens.home.Screens
import com.filipeoliveira.appdevendas.ui.theme.AppDeVendasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDeVendasTheme {
                val navController = rememberNavController()

                val screensInBottomNav = listOf(
                    Screens.Home
                )

                Scaffold(
                    bottomBar = { SalesBottomNavigation(navController, screensInBottomNav) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ){
                        composable(Screens.Home.route) { HomeScreen() }
                    }
                }
            }
        }
    }
}