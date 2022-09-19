package com.example.vamatest.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vamatest.ui.screens.albumDetails.AlbumDetailsScreen
import com.example.vamatest.ui.screens.albumsList.AlbumsListScreen

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Route.ALBUMS_LIST
    ) {
        composable(
            route = Navigation.Route.ALBUMS_LIST
        ) {
            AlbumsListScreen {
                navController.toAlbumDetailsScreen()
            }
        }
        composable(
            route = Navigation.Route.ALBUM_DETAILS
        ) {
            AlbumDetailsScreen()
        }
    }
}

object Navigation {

    object Arg {
    }

    object Route {
        const val ALBUMS_LIST = "albumsList"
        const val ALBUM_DETAILS = "albumDetails"
    }
}

fun NavController.toAlbumDetailsScreen() {
    navigate(route = Navigation.Route.ALBUM_DETAILS)
}
