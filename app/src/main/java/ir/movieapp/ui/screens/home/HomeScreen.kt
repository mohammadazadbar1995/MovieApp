package ir.movieapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import ir.movieapp.R
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryPink

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(

) {

    Scaffold(
        topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
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
                    colors = topAppBarColors(containerColor = primaryDark),
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

        }
    }

}
