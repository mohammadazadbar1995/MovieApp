package ir.movieapp.ui.screens.account

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.ui.theme.primaryPink

@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 120.dp)
                .padding(top = 32.dp),
            painter = painterResource(id = R.drawable.movieapp),
            contentDescription = "Image",
        )

        Spacer(modifier = Modifier.size(60.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Log.d("AccountScreen", "Profile clicked")
                },

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                )
                Text(text = "About")
            }

            Divider(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
        }
    }
}