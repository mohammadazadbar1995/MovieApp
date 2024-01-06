package ir.movieapp.ui.screens.commons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.movieapp.ui.screens.navigation.BottomNavigationItem

@Composable
fun StandardScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    BottomNavigationItem().bottomNavigationItems()
                        .forEachIndexed { index, navigationItem ->

                            //iterating all items with their respective indexes
                            NavigationBarItem(
                                selected = currentDestination?.route?.contains(navigationItem.destination.route) == true,
                                label = {
                                    Text(navigationItem.title)
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = navigationItem.icon),
                                        contentDescription = navigationItem.title
                                    )
                                },
                                onClick = {
                                    navController.navigate(navigationItem.destination.route) {
                                        navController.graph.startDestinationRoute?.let { screen_route ->
                                            popUpTo(screen_route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            )
                        }
                }
            }

        }

    ) {
        content(it)
    }
}