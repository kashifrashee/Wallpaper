package com.example.wallpaper.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper.R
import com.example.wallpaper.data.Wallpapers
import com.example.wallpaper.retroFit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WallpapersViewModel : ViewModel() {
    private val _wallpapers = MutableStateFlow<List<Wallpapers>>(emptyList())
    val wallpapers: StateFlow<List<Wallpapers>> = _wallpapers

    private val pexelsApiService = RetrofitInstance.api
    private val pexelsApiKey = "kbdkfVru4mFfzd18znFkGFqhLzunqI4o9Ma9u0mhrdkpyKAh5jlpEneI"

    fun fetchWallpapers(category: String){
        when(category){
            R.string.football_players.toString() -> {
                _wallpapers.value = listOf(
                    Wallpapers("1", "https://w0.peakpx.com/wallpaper/250/666/HD-wallpaper-cristiano-ronaldo-cr7-cristiano-ronaldo-ronaldo-thumbnail.jpg"),
                    Wallpapers("2", "https://w0.peakpx.com/wallpaper/704/825/HD-wallpaper-cr7-cristiano-ronaldo-ronaldo-thumbnail.jpg"),
                    Wallpapers("3", "https://w0.peakpx.com/wallpaper/885/371/HD-wallpaper-ronaldo-man-u-manchester-united-ronaldo-cristiano-ronaldo-cristiano-ronaldo-manchester-cr7-manchester-united-thumbnail.jpg"),
                    Wallpapers("4", "https://w0.peakpx.com/wallpaper/226/19/HD-wallpaper-ronaldo-die-hard-fan-goat-thumbnail.jpg"),
                    Wallpapers("5","https://w0.peakpx.com/wallpaper/875/364/HD-wallpaper-ronaldo-cr7-cristiano-thumbnail.jpg"),
                    Wallpapers("6","https://w0.peakpx.com/wallpaper/780/696/HD-wallpaper-cristiano-ronaldo-cr7-cristiano-cristianoronaldo-football-king-legend-madrid-portugal-real-madrid-ronaldo-thumbnail.jpg"),
                    Wallpapers("7","https://w0.peakpx.com/wallpaper/807/847/HD-wallpaper-cristiano-ronaldo-mun-champions-league-premiere-league-cristiano-ronaldo-manchester-united-football-thumbnail.jpg"),
                    Wallpapers("8","https://w0.peakpx.com/wallpaper/879/836/HD-wallpaper-cristiano-ronaldo-cr7-cristiano-juventus-ronaldo-thumbnail.jpg"),
                    Wallpapers("9","https://w0.peakpx.com/wallpaper/694/105/HD-wallpaper-ronaldo-cr7-cristiano-thumbnail.jpg"),
                    Wallpapers("10","https://w0.peakpx.com/wallpaper/950/87/HD-wallpaper-cr7-manchester-united-cristiano-ronaldo-cristiano-ronaldo-manchester-thumbnail.jpg"),
                    Wallpapers("11","https://i.pinimg.com/474x/80/8c/df/808cdf414fddbf9429eaab98da821210.jpg"),
                    Wallpapers("12","https://i.pinimg.com/236x/88/40/42/884042d7a788474cc3d3fdc256ef0a03.jpg"),
                    Wallpapers("13","https://i.pinimg.com/474x/ce/16/25/ce16258e900a3ed1450bcbe3f0c6a76e.jpg"),
                    Wallpapers("14","https://i.pinimg.com/474x/21/a8/3d/21a83dfd0ed7d21afb5b72d891132060.jpg"),
                    Wallpapers("15","https://i.pinimg.com/474x/ee/5c/10/ee5c10670180f5a667a4f79ec15e3970.jpg"),
                    Wallpapers("16","https://i.pinimg.com/474x/14/cf/bd/14cfbd71185f3885c41793346fce0b00.jpg"),
                    Wallpapers("17","https://i.pinimg.com/474x/6e/72/92/6e72927caf63aa42e76778424605a0a7.jpg"),
                    Wallpapers("18","https://i.pinimg.com/474x/0e/4e/b9/0e4eb9f6f7ab68dd00d21f8a526fb476.jpg"),
                    Wallpapers("19","https://i.pinimg.com/236x/8f/71/ae/8f71ae45e97b537e45f05bf74ccd6a4a.jpg"),
                    Wallpapers("20","https://i.pinimg.com/474x/25/a4/9c/25a49cd2b824345983356616977080cf.jpg"),
                    Wallpapers("20","https://i.pinimg.com/474x/48/08/2f/48082f6f71a4e1f48736cf53b158b3ac.jpg"),
                    Wallpapers("22","https://i.pinimg.com/236x/fa/df/e2/fadfe2c3bc3d9492d590f0d5220a4db0.jpg"),
                    Wallpapers("23","https://i.pinimg.com/474x/6e/0e/21/6e0e218bc2828f27d22f1b4d14b3ac12.jpg"),
                    Wallpapers("24","https://i.pinimg.com/236x/1b/87/b7/1b87b713c59c118a5ce7e1fd18e2db87.jpg"),
                    Wallpapers("25","https://i.pinimg.com/474x/55/cb/1b/55cb1b0365ad8514ecab96f3e23835f9.jpg"),
                    Wallpapers("26","https://i.pinimg.com/474x/5b/f0/c3/5bf0c38be5f1e4d3748145dd7a9db006.jpg"),
                    Wallpapers("27","https://i.pinimg.com/236x/90/6c/cc/906ccc0f7517bf80b98cfeee56fed3c5.jpg"),
                    Wallpapers("28","https://i.pinimg.com/236x/c9/da/94/c9da948103e735cb1f9ac1c1f5ed12d7.jpg"),
                    Wallpapers("29","https://i.pinimg.com/236x/a6/ba/46/a6ba46e4c2ba063d02356bc0d913cb34.jpg"),
                    Wallpapers("30","https://w0.peakpx.com/wallpaper/662/5/HD-wallpaper-cristiano-ronaldo-black-cool-cr7-halamadrid-iphone-juventus-real-madrid-samsung-thumbnail.jpg"),
                    Wallpapers("31","https://w0.peakpx.com/wallpaper/545/360/HD-wallpaper-cristiano-ronaldo-cr7-cristiano-juve-juventus-messi-neymar-real-madrid-ronaldo-thumbnail.jpg"),
                    Wallpapers("32","https://w0.peakpx.com/wallpaper/73/593/HD-wallpaper-christiano-ronaldo-beast-cr7-football-goat-juventus-portugal-real-madrid-ronaldo-soccer-trophy-thumbnail.jpg"),
                    Wallpapers("33","https://w0.peakpx.com/wallpaper/768/543/HD-wallpaper-cristiano-ronaldo-juventus-legend-real-madrid-thumbnail.jpg"),
                    Wallpapers("34","https://w0.peakpx.com/wallpaper/527/910/HD-wallpaper-cr7-cristiano-futbol-juventus-manchester-real-madrid-ronaldo-soccer-united-thumbnail.jpg"),
                    Wallpapers("35","https://w0.peakpx.com/wallpaper/464/737/HD-wallpaper-cristiano-ronaldo-cr7-thumbnail.jpg"),
                    Wallpapers("36","https://w0.peakpx.com/wallpaper/703/889/HD-wallpaper-cristiano-ronaldo-drawing-juventus-king-machine-thumbnail.jpg"),
                    Wallpapers("37","https://w0.peakpx.com/wallpaper/783/191/HD-wallpaper-ronaldo-cr7-cristiano-footballeur-juventus-portugal-thumbnail.jpg"),
                    Wallpapers("38","https://w0.peakpx.com/wallpaper/249/517/HD-wallpaper-cristiano-ronaldo-champion-sport-ucl-thumbnail.jpg"),
                    Wallpapers("39","https://w0.peakpx.com/wallpaper/532/783/HD-wallpaper-cristiano-ronaldo-football-soccer-thumbnail.jpg"),
                    Wallpapers("40","https://w0.peakpx.com/wallpaper/816/963/HD-wallpaper-cr7-ronaldo-thumbnail.jpg"),
                    Wallpapers("45","https://w0.peakpx.com/wallpaper/697/73/HD-wallpaper-cr7-black-cristiano-ronaldo-thumbnail.jpg"),
                    Wallpapers("46","https://w0.peakpx.com/wallpaper/267/616/HD-wallpaper-cristiano-ronaldo-mac-futbolcu-ekran-futball-thumbnail.jpg"),
                    Wallpapers("47","https://w0.peakpx.com/wallpaper/273/306/HD-wallpaper-players-manchester-united-cristiano-ronaldo-7-ronaldo-siii-cristiano-ronaldo-sin-camisa-cr7-manchester-united-thumbnail.jpg"),
                    Wallpapers("48","https://w0.peakpx.com/wallpaper/540/179/HD-wallpaper-cristiano-ronaldo-portugal-men-soccer-thumbnail.jpg"),
                    Wallpapers("49","https://w0.peakpx.com/wallpaper/558/870/HD-wallpaper-ronaldo-art-cr7-cristiano-ronaldo-football-player-real-madrid-sport-thumbnail.jpg"),
                    Wallpapers("50","https://w0.peakpx.com/wallpaper/597/363/HD-wallpaper-cristiano-ronaldo-cr7-cristiano-ronaldo-juventus-portugal-thumbnail.jpg"),
                    Wallpapers("51","https://w0.peakpx.com/wallpaper/234/189/HD-wallpaper-cristiano-ronaldo-logo-cristiano-ronaldo-logo-cr7-autograph-footballer-thumbnail.jpg"),
                    Wallpapers("52","https://w0.peakpx.com/wallpaper/334/598/HD-wallpaper-ronaldo-cristiano-ronaldo-juventus-juve-football-thumbnail.jpg"),
                    Wallpapers("53","https://w0.peakpx.com/wallpaper/138/873/HD-wallpaper-cr7-y-messi-cristiano-ronaldo-cristiano-y-messi-cristino-ronaldo-y-messi-futbol-messi-y-cr7-messi-y-cristiano-ronaldo-messi-y-ronaldo-thumbnail.jpg"),
                    Wallpapers("54","https://w0.peakpx.com/wallpaper/907/356/HD-wallpaper-cristiano-ronaldo-manchester-football-manchester-united-thumbnail.jpg"),
                    Wallpapers("55","https://w0.peakpx.com/wallpaper/229/896/HD-wallpaper-ronaldo-juventus-football-cristiano-ronaldo-thumbnail.jpg"),
                    Wallpapers("56","https://w0.peakpx.com/wallpaper/763/240/HD-wallpaper-ronaldo-cristiano-thumbnail.jpg"),
                    Wallpapers("57","https://w0.peakpx.com/wallpaper/393/737/HD-wallpaper-ronaldo-2018-football-thumbnail.jpg"),
                    Wallpapers("58","https://w0.peakpx.com/wallpaper/763/881/HD-wallpaper-ronaldo-and-messi-shaking-hand-in-black-jersey-ronaldo-and-messi-messi-and-ronaldo-shaking-hand-black-jersey-footballer-sportsman-thumbnail.jpg"),
                    Wallpapers("59","https://w0.peakpx.com/wallpaper/501/698/HD-wallpaper-cristiano-ronaldo-cristiano-football-madrid-real-ronaldo-sports-thumbnail.jpg"),
                    Wallpapers("60","https://w0.peakpx.com/wallpaper/878/533/HD-wallpaper-cristiano-ronaldo-champion-cr7-portugal-thumbnail.jpg"),
                    Wallpapers("61","https://i.pinimg.com/474x/5f/cb/74/5fcb74ea2027ea70b2f50e28f36acde8.jpg"),
                    Wallpapers("62","https://i.pinimg.com/236x/fd/c2/f5/fdc2f5cb9662d440fc985ecac4b535a3.jpg"),
                    Wallpapers("63","https://i.pinimg.com/474x/a2/2f/03/a22f03cfb35a615bb4d4cb5ce3eecfd0.jpg"),
                    Wallpapers("64","https://i.pinimg.com/474x/73/8e/d1/738ed1e60d6f80a90ef62496f256b7ca.jpg"),
                    Wallpapers("65","https://i.pinimg.com/474x/04/8a/8e/048a8e469842235294911fbfdc4c3583.jpg"),
                    Wallpapers("66","https://i.pinimg.com/236x/2f/12/88/2f128870fa0a585bd56e1f87b1fa5bfd.jpg"),
                    Wallpapers("67","https://i.pinimg.com/236x/b4/68/1b/b4681b199a128bc2701d21fe541f84d4.jpg"),
                    Wallpapers("68","https://i.pinimg.com/474x/58/a1/26/58a126dbe8c1fcf72ba10e7962884333.jpg"),
                    Wallpapers("69","https://i.pinimg.com/474x/b8/ad/17/b8ad178f29ffa78e3c6041c35a6729bd.jpg"),
                    Wallpapers("70","https://i.pinimg.com/236x/c0/d7/4e/c0d74e49e8a586f07c4de1269694ccba.jpg"),
                    Wallpapers("71","https://i.pinimg.com/236x/0d/c6/69/0dc669ea122df78db910a8d12affe2af.jpg"),
                    Wallpapers("72","https://i.pinimg.com/236x/a0/e0/43/a0e0434d26cd98024e7daa0a121c7697.jpg"),
                    Wallpapers("73","https://i.pinimg.com/236x/21/15/ef/2115ef8ed200d737048bc1ad8c15c799.jpg"),
                    Wallpapers("74","https://i.pinimg.com/236x/23/0b/2b/230b2b80c9d90119ae8e51a93bc1d648.jpg"),
                    Wallpapers("75","https://i.pinimg.com/236x/c2/51/b6/c251b6e43da2a71777a6a5693d9ada41.jpg"),
                    Wallpapers("76","https://i.pinimg.com/236x/c3/74/53/c37453f37730acdf08e79f81bd1e52b1.jpg"),
                    Wallpapers("77","https://i.pinimg.com/236x/f9/02/c0/f902c0d6969f129aff7c60cdb27e842f.jpg"),
                    Wallpapers("78","https://i.pinimg.com/474x/9b/dc/34/9bdc34c774215848234d3f1d56db3122.jpg"),
                    Wallpapers("79","https://i.pinimg.com/474x/85/23/5b/85235b5244ef4bea283a77a5d2fd9f21.jpg"),
                    Wallpapers("80","https://i.pinimg.com/474x/36/e8/1c/36e81c2d3af7a45d884608517a7505d7.jpg"),
                    Wallpapers("81","https://i.pinimg.com/236x/40/d6/91/40d6913a7090a2604e4214afc77ce861.jpg"),
                    Wallpapers("82","https://i.pinimg.com/236x/31/e1/03/31e103b5cd8c4d125d11c410cef2c026.jpg"),
                    Wallpapers("83","https://i.pinimg.com/736x/08/5c/e1/085ce1ea1c35f4f5f61a0c27c3b8ecf0.jpg"),
                    Wallpapers("84","https://i.pinimg.com/474x/4d/80/2b/4d802b4340de4a9eae78e10a5edd70b8.jpg"),
                    Wallpapers("85","https://i.pinimg.com/236x/42/ec/f5/42ecf5b413420d2a2058760daf3d1fac.jpg"),
                    Wallpapers("86","https://i.pinimg.com/236x/9b/d4/a1/9bd4a143e81a0145f5a8164c727583fe.jpg"),
                    Wallpapers("87","https://i.pinimg.com/474x/95/3c/b7/953cb7977ad00c2bd3a7974a47aedbd6.jpg"),
                    Wallpapers("88","https://i.pinimg.com/474x/a9/0b/15/a90b1541db9b72a5141bf0fe345b32b5.jpg"),
                    Wallpapers("89","https://i.pinimg.com/236x/cf/59/82/cf59824a77ac869a011be92ee4b2a381.jpg"),
                    Wallpapers("90","https://i.pinimg.com/236x/89/2d/07/892d070a46377b8976c24bbd5fa30b67.jpg"),
                    Wallpapers("91","https://i.pinimg.com/236x/a6/03/ac/a603ac4339f0d0965ab74aca4bf71b63.jpg"),
                    Wallpapers("92","https://i.pinimg.com/236x/04/7f/a8/047fa859fefb8ef3f7b6886ba7c8f443.jpg"),
                    Wallpapers("93","https://i.pinimg.com/236x/f8/b2/6b/f8b26b016925e49b77960b5e28bc33c9.jpg"),
                    Wallpapers("94","https://i.pinimg.com/236x/4f/5c/ad/4f5cade6f3ff367b2dab5c41502576fb.jpg"),
                    Wallpapers("95","https://i.pinimg.com/236x/77/b1/33/77b1334a110b7cc2433cb1141fadad82.jpg"),
                    Wallpapers("96","https://i.pinimg.com/236x/0b/10/62/0b1062558acd6c1dc82005e1961917a7.jpg"),
                    Wallpapers("97","https://i.pinimg.com/236x/b8/9b/89/b89b8967710155d3b781e869abc2a511.jpg"),
                    Wallpapers("98","https://i.pinimg.com/236x/76/13/b5/7613b55f9e0de184b7cb9d355a8ccd1d.jpg"),
                    Wallpapers("99","https://i.pinimg.com/236x/69/91/91/69919156194912893b4d73724dc0134f.jpg"),
                    Wallpapers("100","https://i.pinimg.com/236x/a3/e7/59/a3e7591e0e5106ac8af1b8133a2fd1b5.jpg")

                )
            }
            R.string.cars_category.toString() -> fetchPexelsImages("cars")
            R.string.bikes_category.toString() -> fetchPexelsImages("bikes")
            R.string.flowers_category.toString() -> fetchPexelsImages("flowers")
            R.string.art_design_category.toString() -> fetchPexelsImages("art and design")
            R.string.movies_tv_shows_category.toString() -> fetchPexelsImages("Movies")
            R.string.nature_category.toString() -> fetchPexelsImages("nature")
            R.string.animals_category.toString() -> fetchPexelsImages("animals")
            R.string.cityscapes_category.toString() -> fetchPexelsImages("cityscapes")
            R.string.sunsets_category.toString() -> fetchPexelsImages("sunsets")
            R.string.abstract_category.toString() -> fetchPexelsImages("abstract")
            R.string.space_category.toString() -> fetchPexelsImages("space")
            R.string.underwater_category.toString() -> fetchPexelsImages("underwater")
            R.string.sports_category.toString() -> fetchPexelsImages("sports")
            R.string.architecture_category.toString() -> fetchPexelsImages("architecture")
            R.string.sky_category.toString() -> fetchPexelsImages("sky")
            R.string.moon_category.toString() -> fetchPexelsImages("moon")
            else -> _wallpapers.value = emptyList()
        }
        Log.d("WallpapersViewModel", "Wallpapers fetched: ${_wallpapers.value}")
    }

    private fun fetchPexelsImages(query: String) {
        viewModelScope.launch {
            try {
                val response = pexelsApiService.getPhotos(
                    apiKey = pexelsApiKey,
                    query = query,
                    perPage = 100,
                    orientation = "portrait"
                )
                if (response.isSuccessful) {
                    val imageUrls = response.body()?.photos?.map { it.src?.original }
                    _wallpapers.value = imageUrls?.mapIndexed { index, url ->
                        Wallpapers(index.toString(), url ?: "")
                    } ?: emptyList()
                } else {
                    Log.e("WallpapersViewModel", "Failed to fetch images: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("WallpapersViewModel", "Exception: ${e.message}")
            }
        }
    }
}