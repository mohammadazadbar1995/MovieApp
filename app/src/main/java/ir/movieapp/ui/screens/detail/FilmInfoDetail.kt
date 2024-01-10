package ir.movieapp.ui.screens.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.ui.screens.commons.CastItemView
import ir.movieapp.ui.theme.primaryPink
import ir.movieapp.util.preview.Constants

@Composable
fun FilmInfoDetail(
    navigator: DestinationsNavigator,
    movieData: MovieDetailResponse,
    casts: List<CreditsResponse.Cast>?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.release_date),
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 4.dp)
        )

        Text(
            text = movieData.releaseDate,
            fontSize = 14.sp,
            color = Color.LightGray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(top = 4.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))

        ExpandableText(text = movieData.overview, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        if (!casts.isNullOrEmpty()) {
            CastView(
                casts = casts,
            )
        }


    }
}

@Composable
fun CastView(
    casts: List<CreditsResponse.Cast>
) {


    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.cast),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.view_all),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }

            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items(casts.size) {
                CastItemView(
                    cast = casts[it]
                )
            }
        }

    }
}


@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    // getting raw values for smart cast
    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Text(
        text = cutText ?: text,
        fontSize = 14.sp,
        color = Color.LightGray,
        fontWeight = FontWeight.Normal,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                expanded = false
            },
        maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResultState.value = it },
    )

    if (!expanded) {
        val destiny = LocalDensity.current

        Text(
            text = "... see more",
            fontSize = 14.sp,
            color = primaryPink,
            fontWeight = FontWeight.Bold,
            onTextLayout = { seeMoreSizeState.value = it.size },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    expanded = true
                    cutText = null
                },
        )
    }
}