package ir.movieapp.ui.screens.cast_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.ui.screens.commons.CastItemView
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryPink

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun CastsScreen(
    creditsResponse: CreditsResponse,
    navigator: DestinationsNavigator,
) {

    Scaffold(
        topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = primaryPink
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        Column {
                            Image(
                                modifier = Modifier
                                    .size(width = 90.dp, height = 90.dp)
                                    .padding(8.dp),
                                painter = painterResource(id = R.drawable.muviz),
                                contentDescription = "Image",
                                colorFilter = ColorFilter.tint(color = primaryPink)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryDark),
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        }
                    },
                )
            }
        }
    ) { it ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it.calculateTopPadding())
        ) {

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = it
        ) {
            items(creditsResponse.casts.size) { castItem ->
                CastItemView(
                    size = 170.dp,
                    cast = creditsResponse.casts[castItem]
                )
            }
        }
    }

}