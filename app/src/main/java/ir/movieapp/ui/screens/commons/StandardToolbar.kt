package ir.movieapp.ui.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryGray
import ir.movieapp.ui.theme.primaryPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardToolbar(
    title :@Composable () -> Unit = {},
    navActions: @Composable RowScope.() -> Unit = {},
    showBackArrow: Boolean = false,
    navigator: DestinationsNavigator,
) {
//    Surface(shadowElevation = 5.dp) {
//        TopAppBar(
//            title = title,
//            navigationIcon = ,
//            colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryDark),
//            actions = navActions,
//        )
//    }
}