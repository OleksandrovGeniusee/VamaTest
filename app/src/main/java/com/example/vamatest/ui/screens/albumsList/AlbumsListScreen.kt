package com.example.vamatest.ui.screens.albumsList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vamatest.ui.theme.VamaTestTheme

@Composable
fun AlbumsListScreen(onAlbumPressed: () -> Unit) {
    AlbumsList(onAlbumPressed = onAlbumPressed)
}

@Composable
private fun AlbumsList(onAlbumPressed: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Albums list")
        Button(
            enabled = true,
            onClick = { onAlbumPressed.invoke() }
        ) {
            Text(text = "Details")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VamaTestTheme {
        AlbumsList(onAlbumPressed = {})
    }
}
