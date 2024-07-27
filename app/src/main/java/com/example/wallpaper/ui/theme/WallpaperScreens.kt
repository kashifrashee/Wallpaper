package com.example.wallpaper.ui.theme

import android.Manifest
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.example.wallpaper.R
import com.example.wallpaper.data.WallpaperCategory
import com.example.wallpaper.model.CategoryModelView
import com.example.wallpaper.model.WallpapersViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


@Composable
fun WallpaperApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            WallpaperAppBar(
                currentScreen = stringResource(id = R.string.app_name),
                showBackButton = navController.previousBackStackEntry != null,
                onBackClick = {navController.popBackStack()}
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "category",
            modifier = Modifier.padding(innerPadding)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperAppBar(
    currentScreen: String,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.Black else Color.White
    TopAppBar(

        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentScreen,
                    color = textColor,
                    fontSize = 50.sp,
                    fontFamily = FontFamily.Cursive,
                    //fontWeight = FontWeight.Bold,
                )
            }
        },
        navigationIcon = (if (showBackButton) {
            {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        } else {
            {}
        }),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
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
            .padding(5.dp)
            .height(250.dp),
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
                fontSize = 45.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp),
                color = Color.White,
                fontFamily = FontFamily.Cursive,

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
                        .height(300.dp)
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
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(model = wallpaperUrl)
    val scope = rememberCoroutineScope()

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
            onClick = {
                Log.d("WallpaperDetailScreen", "Set Wallpaper button clicked")
                CoroutineScope(Dispatchers.IO).launch {
                    val request = ImageRequest.Builder(context)
                        .data(wallpaperUrl)
                        .allowHardware(false) // Ensure it's a software bitmap
                        .build()
                    val result = (painter.imageLoader.execute(request) as SuccessResult).drawable
                    val bitmap = (result as BitmapDrawable).bitmap
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    wallpaperManager.setBitmap(bitmap)

                    try {
                        wallpaperManager.setBitmap(bitmap)
                        // Show a Toast message on the main thread
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, "Wallpaper has been set", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
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
            onClick = {
                Log.d("WallpaperDetailScreen", "Download button clicked")
                // Request permission and download
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                    scope.launch {
                        downloadAndSaveImage(context, wallpaperUrl)
                    }
                } else {
                    ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_PERMISSION_CODE)
                }
            },
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


private suspend fun downloadAndSaveImage(context: Context, imageUrl: String) {
    try {
        // Use a coroutine to handle network and file operations
        withContext(Dispatchers.IO) {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val fileName = "wallpaper_${System.currentTimeMillis()}.jpg"
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    fileName
                )
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

                // Notify the media scanner
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(file.absolutePath),
                    null
                ) { _, uri ->
                    Log.d("Download", "Image saved to gallery: $uri")
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Wallpaper downloaded and saved", Toast.LENGTH_SHORT).show()
                }
            } else {
                throw IOException("HTTP error: ${connection.responseCode}")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to download wallpaper: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

private const val REQUEST_PERMISSION_CODE = 1001

@Preview(showBackground = true)
@Composable
fun ShowFullImagePreview() {
    WallpaperTheme {
        //WallpaperDetailScreen(wallpaperUrl = "https://w0.peakpx.com/wallpaper/120/285/HD-wallpaper-ronaldo-and-messi-playing-chess-ronaldo-and-messi-footballer-sportsman-thumbnail.jpg")
        WallpaperApp()
    }
}
