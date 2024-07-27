package com.example.wallpaper.model

import androidx.lifecycle.ViewModel
import com.example.wallpaper.R
import com.example.wallpaper.data.WallpaperCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryModelView : ViewModel() {
    private val _wallpaperCategories = MutableStateFlow<List<WallpaperCategory>>(emptyList())
    val wallpaperCategories: StateFlow<List<WallpaperCategory>> get()= _wallpaperCategories

    fun loadWallpaperCategories(){
        _wallpaperCategories.value = listOf(
            WallpaperCategory("https://images.pexels.com/photos/262367/pexels-photo-262367.jpeg?auto=compress&cs=tinysrgb&w=600", R.string.architecture_category),
            WallpaperCategory("https://images2.alphacoders.com/665/thumb-440-66550.webp", R.string.animals_category),
            WallpaperCategory("https://i.pinimg.com/236x/c6/f2/57/c6f2577863921004e73327570286c289.jpg", R.string.abstract_category),
            WallpaperCategory("https://instructor-academy.onlinecoursehost.com/content/images/2023/05/58_Top-10-Successful-Graphic-Design-Course-Creators.jpg", R.string.art_design_category),
            WallpaperCategory("https://images6.alphacoders.com/476/thumb-1920-476941.jpg", R.string.bikes_category),
            WallpaperCategory("https://images.pexels.com/photos/919073/pexels-photo-919073.jpeg?auto=compress&cs=tinysrgb&w=600", R.string.cars_category),
            WallpaperCategory("https://images.alphacoders.com/938/thumbbig-938482.webp", R.string.cityscapes_category),
            WallpaperCategory("https://cdn.pixabay.com/photo/2021/07/12/19/40/football-6421158_640.jpg", R.string.football_players),
            WallpaperCategory("https://images4.alphacoders.com/133/thumb-440-1339103.webp", R.string.flowers_category),
            WallpaperCategory("https://images.pexels.com/photos/3567673/pexels-photo-3567673.jpeg?auto=compress&cs=tinysrgb&w=600", R.string.moon_category),
            WallpaperCategory("https://w0.peakpx.com/wallpaper/256/319/HD-wallpaper-2018-venom-movie-poster-venom-movie-venom-2018-movies-movies-thumbnail.jpg", R.string.movies_tv_shows_category),
            WallpaperCategory("https://picfiles.alphacoders.com/320/320687.jpg", R.string.nature_category),
            WallpaperCategory("https://i.pinimg.com/236x/1f/4e/d1/1f4ed1891724c488111f8be449d4529a.jpg", R.string.sunsets_category),
            WallpaperCategory("https://i.pinimg.com/236x/bf/80/a4/bf80a40fb1f9ed4711df31f90d5b42ac.jpg", R.string.space_category),
            WallpaperCategory("https://i.pinimg.com/236x/0f/02/49/0f02491560476670dd13547f7d2a565a.jpg", R.string.sky_category),
            WallpaperCategory("https://i.pinimg.com/474x/7c/5f/36/7c5f3663e242466e15863908b5c22e4d.jpg", R.string.underwater_category),
            WallpaperCategory("https://images.pexels.com/photos/209977/pexels-photo-209977.jpeg?auto=compress&cs=tinysrgb&w=600", R.string.sports_category),
        )
    }
}