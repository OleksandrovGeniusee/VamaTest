package com.example.vamatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import com.example.vamatest.navigation.AppNavigation
import com.example.vamatest.ui.theme.VamaTestTheme

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            VamaTestTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation()
            }
        }
    }
}
