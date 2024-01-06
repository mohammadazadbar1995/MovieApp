package ir.movieapp.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ir.movieapp.R
import ir.movieapp.ui.screens.destinations.AccountScreenDestination
import ir.movieapp.ui.screens.destinations.Destination
import ir.movieapp.ui.screens.destinations.FavoriteScreenDestination
import ir.movieapp.ui.screens.destinations.HomeScreenDestination

data class BottomNavigationItem(
    var title: String = "",
    var icon: Int = R.drawable.ic_home,
    var destination: Destination = HomeScreenDestination
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = "Home",
                icon = R.drawable.ic_home,
                destination = HomeScreenDestination
            ),
            BottomNavigationItem(
                title = "Favorites",
                icon = R.drawable.ic_star,
                destination = FavoriteScreenDestination
            ),
            BottomNavigationItem(
                title = "Account",
                icon = R.drawable.ic_profile,
                destination = AccountScreenDestination
            ),
        )
    }
}