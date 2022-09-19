package com.example.vamatest.ui.screens.albumDetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vamatest.ui.theme.VamaTestTheme

@Composable
fun AlbumDetailsScreen() {
    AlbumDetails()
}

@Composable
private fun AlbumDetails() {
    Text("Details")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VamaTestTheme {
        AlbumDetails()
    }
}
