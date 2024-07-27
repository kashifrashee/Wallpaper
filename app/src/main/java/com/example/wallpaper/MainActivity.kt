package com.example.wallpaper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

import com.example.wallpaper.ui.theme.WallpaperApp
import com.example.wallpaper.ui.theme.WallpaperTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallpaperTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    WallpaperApp()
                }
            }
        }
    }
}


