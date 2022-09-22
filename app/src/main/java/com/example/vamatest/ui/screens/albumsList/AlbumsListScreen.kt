package com.example.vamatest.ui.screens.albumsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vamatest.R
import com.example.vamatest.ui.common.ClickableItemsGrid
import com.example.vamatest.ui.theme.VamaTestTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vama.domain.Album
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterial3Api
@Composable
fun AlbumsListScreen(onAlbumItemClicked: (Album) -> Unit) {
    val viewModel = getViewModel<AlbumsListViewModel>()
    val albums by viewModel.albumsList.observeAsState()
    viewModel.getAlbums()

    AlbumsList(albums = albums, onAlbumItemClicked = onAlbumItemClicked)
}

@ExperimentalMaterial3Api
@Composable
private fun AlbumsList(
    albums: List<Album>?,
    onAlbumItemClicked: (Album) -> Unit
) {
    val viewModel = getViewModel<AlbumsListViewModel>()
    val isRefreshing by viewModel.isRefreshing.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing == true),
            onRefresh = { viewModel.getAlbums() }
        ) {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MediumTopAppBar(
                        title = {
                            Text(
                                text = stringResource(id = R.string.top_hundred_albums),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = colorResource(id = R.color.background)
                        )
                    )
                },
                content = { innerPadding ->
                    albums?.let {
                        ClickableItemsGrid(
                            albums = albums,
                            onItemClicked = onAlbumItemClicked,
                            contentPadding = innerPadding
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun ErrorView() {
    val viewModel = getViewModel<AlbumsListViewModel>()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material.Text(
            text = stringResource(id = R.string.bad_internet_connection_text),
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_item_space_default)),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.album_artist_name_list)
            )
        )
        Button(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_item_space_extra_big)),
            onClick = {
                viewModel.getAlbums()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bright_blue))
        ) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.retry),
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VamaTestTheme {
        AlbumsList(
            listOf(
                Album(
                    id = "1622045624",
                    name = "Un Verano Sin Ti",
                    artistName = "Bad Bunny",
                    releaseDate = "2022-05-06",
                    kind = "albums",
                    artistId = "1126808565",
                    artistUrl = "https://music.apple.com/us/artist/bad-bunny/1126808565",
                    contentAdvisoryRating = "Explict",
                    artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                    genres = listOf(),
                    url = "https://music.apple.com/us/album/un-verano-sin-ti/1622045624",
                    copyright = "Copyright © 2022 Apple Inc. All rights reserved."
                )
            ),
            onAlbumItemClicked = {}
        )
    }
}
