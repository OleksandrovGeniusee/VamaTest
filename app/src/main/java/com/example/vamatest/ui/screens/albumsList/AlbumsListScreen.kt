package com.example.vamatest.ui.screens.albumsList

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.vamatest.ui.common.AlbumVerticalGrid
import com.example.vamatest.ui.common.ErrorDialog
import com.example.vamatest.ui.common.TopBar
import com.example.vamatest.ui.theme.VamaTestTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vama.data.mockedData.ALBUM
import com.vama.data.models.Album
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterial3Api
@Composable
fun AlbumsListScreen(onAlbumItemClicked: (Album) -> Unit) {
    val viewModel = getViewModel<AlbumsListViewModel>()
    val albums by viewModel.albumsList.observeAsState()
    LaunchedEffect("AlbumsListScreen", block = {
        viewModel.updateAlbums()
    })

    AlbumsList(albums = albums, onAlbumItemClicked = onAlbumItemClicked)
}

@ExperimentalMaterial3Api
@Composable
private fun AlbumsList(
    albums: List<Album>?,
    onAlbumItemClicked: (Album) -> Unit
) {
    val viewModel = getViewModel<AlbumsListViewModel>()
    val isRefreshing by viewModel.loading.observeAsState()
    val error by viewModel.error.observeAsState()
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    if (error != null) {
        ErrorDialog(onDismiss = { viewModel.cleanError() }) {
            viewModel.cleanError()
            viewModel.updateAlbums()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing == true),
            onRefresh = { viewModel.updateAlbums() }
        ) {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopBar(scrollBehavior = scrollBehavior)
                },
                content = { innerPadding ->
                    albums?.let {
                        AlbumVerticalGrid(
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VamaTestTheme {
        AlbumsList(
            listOf(ALBUM),
            onAlbumItemClicked = {}
        )
    }
}
