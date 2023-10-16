package com.filipeoliveira.appdevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filipeoliveira.appdevendas.ui.components.SalesBottomNavigation
import com.filipeoliveira.appdevendas.ui.dimen2Dp
import com.filipeoliveira.appdevendas.ui.screens.home.ScreenHome
import com.filipeoliveira.appdevendas.ui.screens.Screens
import com.filipeoliveira.appdevendas.ui.screens.cart.ScreenCart
import com.filipeoliveira.appdevendas.ui.screens.orders.ScreenOrders
import com.filipeoliveira.appdevendas.ui.theme.AppDeVendasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDeVendasTheme {
                val navController = rememberNavController()

                val screensInBottomNav = listOf(
                    Screens.Home,
                    Screens.Cart,
                    Screens.Orders
                )

                Scaffold(
                    topBar = {
                        Surface(shadowElevation = dimen2Dp) {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = stringResource(R.string.app_name),
                                    )
                                }
                            )
                        }
                    },
                    bottomBar = { SalesBottomNavigation(navController, screensInBottomNav) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Screens.Home.route) {
                            ScreenHome(
                                onGoToCartClicked = {
                                    navController.navigate(Screens.Cart.route) {
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
                        composable(Screens.Orders.route) { ScreenOrders() }
                    }
                }
            }
        }
    }
}