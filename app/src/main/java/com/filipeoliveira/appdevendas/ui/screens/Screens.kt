package com.filipeoliveira.appdevendas.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.filipeoliveira.appdevendas.R

sealed class Screens(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Home : Screens("home", R.string.title_home, Icons.Default.Home)
    object Cart : Screens("cart", R.string.title_cart, Icons.Default.ShoppingCart)
    object Orders : Screens("orders", R.string.title_orders, Icons.Default.AccountBox)
}