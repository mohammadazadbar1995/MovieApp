package ir.movieapp.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.util.preview.ThemePreviews
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

       /* LaunchedEffect{
            delay(3000)
            navigator.navigate(navigator)
        }*/
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(8.dp)
                .align(alignment = Alignment.Center),
            painter = painterResource(id = R.drawable.muviz),
            contentDescription = "App-logo"
        )


    }
}


@ThemePreviews
@Composable
fun SplashScreenPreview() {
//    SplashScreen()
}
