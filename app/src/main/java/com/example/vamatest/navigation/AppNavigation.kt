package com.example.vamatest.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vamatest.navigation.Navigation.Arg.ALBUM_ID
import com.example.vamatest.ui.screens.albumDetails.AlbumDetailsScreen
import com.example.vamatest.ui.screens.albumsList.AlbumsListScreen

@ExperimentalMaterial3Api
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
                navController.toAlbumDetailsScreen(it.id)
            }
        }
        composable(
            route = Navigation.Route.ALBUM_DETAILS,
            arguments = listOf(
                navArgument("albumId") {
                    type = NavType.StringType
                }
            )
        ) {
            AlbumDetailsScreen(it.arguments?.getString(ALBUM_ID)) {
                navController.popBackStack()
            }
        }
    }
}

object Navigation {

    object Arg {
        const val ALBUM_ID = "albumId"
    }

    object Route {
        const val ALBUMS_LIST = "albumsList"
        const val ALBUM_DETAILS = "albumDetails/{albumId}"
    }
}

fun NavController.toAlbumDetailsScreen(albumId: String) {
    navigate(route = "albumDetails/".plus(albumId))
}
