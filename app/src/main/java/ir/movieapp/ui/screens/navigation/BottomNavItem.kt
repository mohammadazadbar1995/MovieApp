package ir.movieapp.ui.screens.navigation

import ir.movieapp.R

sealed class BottomNavItem(val route: String) {
    object Home : BottomNavItem("home_route")
    object Favorites : BottomNavItem("favorites_route")
    object Account : BottomNavItem("account_route")
}