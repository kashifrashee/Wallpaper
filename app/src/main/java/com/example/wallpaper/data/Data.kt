package com.example.wallpaper.data

import androidx.annotation.StringRes

data class Wallpapers(
    val id: String,
    val wallpaperImageResId: String
)

data class WallpaperCategory(
    val imageResId: String,
    @StringRes val wallpaperCategoryTitle: Int,
)