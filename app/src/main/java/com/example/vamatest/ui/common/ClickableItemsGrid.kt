package com.example.vamatest.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vamatest.R
import com.example.vamatest.ui.theme.VamaTestTheme
import com.vama.domain.Album
import com.vama.domain.Gener

@Composable
fun ClickableItemsGrid(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    cellsInRow: Int = 2,
    contentPadding: PaddingValues,
    onItemClicked: (Album) -> Unit
) {
    LazyVerticalGrid(
        contentPadding = contentPadding,
        modifier = modifier.background(colorResource(id = R.color.background))
            .padding(horizontal = dimensionResource(id = R.dimen.margin_item_space_default)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.margin_item_space_small)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.margin_item_space_small),
            Alignment.CenterHorizontally
        ),
        columns = GridCells.Fixed(cellsInRow)
    ) {
        items(albums.size) { index ->
            AlbumItem(album = albums[index], onItemClicked)
        }
    }
}

@Composable
private fun AlbumItem(
    album: Album,
    onItemClicked: (Album) -> Unit
) {
    Box(
        modifier = Modifier.wrapContentSize()
            .clickable {
                onItemClicked.invoke(album)
            }
    ) {
        AsyncImage(
            model = album.artworkUrl100,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.album_image_height))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.album_corner_radius)))
        )
        Column(
            modifier = Modifier.matchParentSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.margin_item_space_small),
                    vertical = dimensionResource(id = R.dimen.margin_item_space_small)
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = album.name,
                maxLines = 2,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.album_name_list)
                )
            )
            Text(
                text = album.artistName,
                maxLines = 2,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.album_artist_name_list)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumItemPreview() {
    VamaTestTheme {
        AlbumItem(
            album = Album(
                id = "1622045624",
                name = "Un Verano Sin Ti",
                artistName = "Bad Bunny",
                releaseDate = "2022-05-06",
                kind = "albums",
                artistId = "1126808565",
                artistUrl = "https://music.apple.com/us/artist/bad-bunny/1126808565",
                contentAdvisoryRating = "Explict",
                artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                genres = listOf(
                    Gener(
                        genreId = "id",
                        name = "Hip Hop",
                        url = "https://music.apple.com/us/artist/bad-bunny/1126808565"
                    ),
                    Gener(
                        genreId = "id1",
                        name = "Rap",
                        url = "https://music.apple.com/us/artist/bad-bunny/1126808565"
                    )
                ),
                url = "https://music.apple.com/us/album/un-verano-sin-ti/1622045624",
                copyright = "Copyright © 2022 Apple Inc. All rights reserved."
            ),
        ) {}
    }
}
