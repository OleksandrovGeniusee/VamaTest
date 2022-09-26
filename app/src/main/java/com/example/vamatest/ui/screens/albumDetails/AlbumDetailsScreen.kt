package com.example.vamatest.ui.screens.albumDetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vamatest.R
import com.example.vamatest.ui.theme.VamaTestTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.vama.data.common.extensions.monthFirstFormat
import com.vama.data.mockedData.ALBUM
import com.vama.domain.models.Album
import com.vama.domain.models.Genre
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun AlbumDetailsScreen(albumId: String?, onBackPressed: () -> Unit) {
    val viewModel = getViewModel<AlbumDetailsViewModel>()
    viewModel.setAlbum(albumId)

    val albumState by viewModel.album.observeAsState()

    albumState?.let {
        ProvideWindowInsets {
            AlbumDetails(it, modifier = Modifier.fillMaxSize(), onBackPressed)
        }
    }
}

@Composable
private fun AlbumDetails(album: Album, modifier: Modifier, onBackPressed: () -> Unit) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = album.artworkUrl100(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            ArtistAndAlbumNames(album = album)
            album.genres()?.let {
                GenersListView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.margin_item_space_default),
                            vertical = dimensionResource(id = R.dimen.margin_item_space_extra_small)
                        ),
                    genersList = it
                )
            }
            BottomInfo(album = album)
            album.albumUrl()?.let { CenteredButton(albumUrl = it) }
            Spacer(
                modifier = Modifier
                    .navigationBarsHeight()
                    .navigationBarsHeight()
            )
        }
        BackButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = dimensionResource(id = R.dimen.margin_item_space_big)),
            onClick = onBackPressed
        )
    }
}

@Composable
private fun CenteredButton(albumUrl: String) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_item_space_extra_big)),
            onClick = {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(albumUrl)
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bright_blue))
        ) {
            Text(
                text = stringResource(id = R.string.visit_the_album),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.album_name_list)
                )
            )
        }
    }
}

@Composable
private fun ArtistAndAlbumNames(album: Album) {
    album.artistName()?.let {
        Text(
            text = it,
            maxLines = 2,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.margin_item_space_default),
                end = dimensionResource(id = R.dimen.margin_item_space_default),
                top = dimensionResource(id = R.dimen.margin_item_space_small)
            ),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.album_artist_name_details)
            )
        )
    }
    album.albumName()?.let {
        Text(
            text = it,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_item_space_default)),
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.album_name_details)
            )
        )
    }
}

@Composable
private fun BottomInfo(album: Album) {
    BottomCommonText(
        stringResource(
            id = R.string.released,
            album.releaseDate()?.monthFirstFormat() ?: album.release() ?: ""
        )
    )
    album.copyright()?.let {
        BottomCommonText(it)
    }
}

@Composable
private fun BottomCommonText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.margin_item_space_default)),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.album_artist_name_list)
        )
    )
}

@Composable
private fun GenersListView(modifier: Modifier, genersList: List<Genre>) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.margin_item_space_extra_small),
            Alignment.Start
        ),
        verticalAlignment = Alignment.Top
    ) {
        genersList.forEach { GenerText(gener = it) }
    }
}

@Composable
private fun GenerText(gener: Genre) {
    Box(
        modifier = Modifier
            .border(
                BorderStroke(
                    1.dp,
                    color = colorResource(R.color.bright_blue)
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.gener_corner_radius))
            )
            .padding(start = 8.dp, top = 3.dp, end = 8.dp, bottom = 4.dp)
    ) {
        gener.name()?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.bright_blue)
                )
            )
        }
    }
}

@Composable
private fun BackButton(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.margin_item_space_default))
            .clickable {
                onClick.invoke()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .background(
                    colorResource(id = R.color.back_button_background_shape),
                    shape = androidx.compose.foundation.shape.CircleShape
                )
        ) {
            Image(
                modifier = Modifier.padding(end = 3.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VamaTestTheme {
        AlbumDetails(
            album = ALBUM,
            modifier = Modifier.fillMaxSize()
        ) {}
    }
}
