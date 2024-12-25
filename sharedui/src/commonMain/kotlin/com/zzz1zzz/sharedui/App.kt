package com.zzz1zzz.sharedui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.zzz1zzz.sharedui.navigation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}