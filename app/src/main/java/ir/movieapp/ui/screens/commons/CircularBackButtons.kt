package ir.movieapp.ui.screens.commons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.movieapp.R
import ir.movieapp.ui.theme.primaryPink

@Composable
fun CircularBackButtons(
    onClick: () -> Unit = {},
    color: Color = Color.Gray,
) {

    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(),
        shape = CircleShape,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White.copy(alpha = 0.3f),
            containerColor = color,
        )
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                tint = primaryPink,
                contentDescription = null
            )
        }
    }
}