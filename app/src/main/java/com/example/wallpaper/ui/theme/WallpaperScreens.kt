package com.example.wallpaper.ui.theme

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.wallpaper.R
import com.example.wallpaper.data.WallpaperCategory
import com.example.wallpaper.model.CategoryModelView
import com.example.wallpaper.model.WallpapersViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun WallpaperApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "category"
    ) {
        composable("category") {
            CategoryScreen(navController = navController)
        }
        composable("wallpapers/{subWallpaper}") { backStackEntry ->
            ShowWallpapers(
                navController = navController,
                subWallpaper = backStackEntry.arguments?.getString("subWallpaper")
            )
        }
        composable(
            "wallpaperDetail/{wallpaperUrl}",
            arguments = listOf(navArgument("wallpaperUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val wallpaperUrl = backStackEntry.arguments?.getString("wallpaperUrl")
            val decodedUrl = wallpaperUrl?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            decodedUrl?.let { WallpaperDetailScreen(wallpaperUrl = it) }
        }
    }
}

@Composable
fun CategoryScreen(
    navController: NavController,
) {
    val categoryViewModel: CategoryModelView = viewModel()
    val categoryList by categoryViewModel.wallpaperCategories.collectAsState()
    LaunchedEffect(Unit) {
        categoryViewModel.loadWallpaperCategories()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
        //.padding(16.dp)
    ) {
        items(categoryList) { wallpaperCategory ->
            WallpaperCategoryImageItem(
                wallpaperCategory = wallpaperCategory,
                modifier = Modifier
                    .clickable {
                        navController.navigate("wallpapers/${wallpaperCategory.wallpaperCategoryTitle}")
                    }
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun WallpaperCategoryImageItem(
    wallpaperCategory: WallpaperCategory,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    val borderColor = if (isDarkTheme) Color.White else Color.Black
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(2.dp, borderColor),
        modifier = modifier
            .padding(top = 8.dp)
            .height(400.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
    ) {
        Box(
            modifier = Modifier.padding(0.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = wallpaperCategory.imageResId
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = wallpaperCategory.wallpaperCategoryTitle),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                color = Color.White,
                fontFamily = FontFamily.Monospace,

                )
        }
    }
}


@Composable
fun ShowWallpapers(
    navController: NavController,
    subWallpaper: String?,
    modifier: Modifier = Modifier,
) {
    val wallpapersViewModel: WallpapersViewModel = viewModel()

    // collect state form the model
    val wallpapers by wallpapersViewModel.wallpapers.collectAsState()

    val isDarkTheme = isSystemInDarkTheme()
    val borderColor = if (isDarkTheme) Color.White else Color.Black

    // fetch wallpapers
    LaunchedEffect(subWallpaper) {
        subWallpaper?.let {
            wallpapersViewModel.fetchWallpapers(it)
        }

    }
    // Check if wallpapers are available
    if (wallpapers.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(wallpapers) { wallpaper ->
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    border = BorderStroke(2.dp, borderColor),
                    modifier = modifier
                        .padding(5.dp)
                        .height(500.dp)
                        .clickable {
                            Log.d(
                                "ShowWallpapers",
                                "Wallpaper URL: ${wallpaper.wallpaperImageResId}"
                            )
                            val encodedUrl = URLEncoder.encode(
                                wallpaper.wallpaperImageResId,
                                StandardCharsets.UTF_8.toString()
                            )
                            navController.navigate("wallpaperDetail/${encodedUrl}")
                        },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    ),

                    ) {
                    Image(
                        painter = rememberImagePainter(data = wallpaper.wallpaperImageResId),
                        contentDescription = wallpaper.id,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }


}

@Composable
fun WallpaperDetailScreen(wallpaperUrl: String) {
    // Remember a state to track if the image is loading
    val imageLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Image that shows up once it's loaded
        Image(
            painter = rememberImagePainter(
                data = wallpaperUrl,
                builder = {
                    // Listener to change the loading state
                    listener(
                        onSuccess = { _, _ -> imageLoading.value = false },
                        onError = { _, _ -> imageLoading.value = false }
                    )
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // CircularProgressIndicator that shows while image is loading
        if (imageLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        // Set Wallpaper button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
                .background(Color.White.copy(alpha = 0.8f))
                .size(50.dp) // Size of the icon
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wallpaper),
                contentDescription = "Set Wallpaper",
                modifier = Modifier
                    .fillMaxSize()
                //.padding(2.dp),
            )
        }
        // Download Wallpaper button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
                .background(Color.White.copy(alpha = 0.8f))
                .size(50.dp) // Size of the icon
        ) {
            Icon(
                painter = painterResource(id = R.drawable.download),
                contentDescription = "Download Wallpaper",
                modifier = Modifier
                    .fillMaxSize()
                //.padding(2.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowFullImagePreview() {
    WallpaperTheme {
        //WallpaperDetailScreen(wallpaperUrl = "https://w0.peakpx.com/wallpaper/120/285/HD-wallpaper-ronaldo-and-messi-playing-chess-ronaldo-and-messi-footballer-sportsman-thumbnail.jpg")
        WallpaperApp()
    }
}
