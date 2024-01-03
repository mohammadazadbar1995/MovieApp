package ir.movieapp.ui.screens.commons

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardToolbar(
    showBackArrow: Boolean = false,
    navigator: DestinationsNavigator,
) {
/*    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = if (showBackArrow) { {
                IconButton(onClick = {
                    navigator.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        } else null,
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
        actions = {
            IconButton(onClick = { *//* doSomething() *//* }) {
                Icon(Icons.Filled.Search, contentDescription = null)
            }
        },
    )*/
}