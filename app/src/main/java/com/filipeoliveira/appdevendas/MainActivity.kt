package com.filipeoliveira.appdevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filipeoliveira.appdevendas.ui.components.SalesBottomNavigation
import com.filipeoliveira.appdevendas.ui.screens.home.ScreenHome
import com.filipeoliveira.appdevendas.ui.screens.Screens
import com.filipeoliveira.appdevendas.ui.screens.cart.ScreenCart
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
                    Screens.Home,
                    Screens.Cart
                )

                Scaffold(
                    bottomBar = { SalesBottomNavigation(navController, screensInBottomNav) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ){
                        composable(Screens.Home.route) { ScreenHome(
                            onGoToCartClicked = {
                                navController.navigate(Screens.Cart.route){
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                        }
                        composable(Screens.Cart.route) { ScreenCart() }
                    }
                }
            }
        }
    }
}