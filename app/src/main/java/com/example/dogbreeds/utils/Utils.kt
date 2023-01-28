package com.example.dogbreeds.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.dogbreeds.domain.model.Breed
import com.example.dogbreeds.domain.model.FavoriteDog

object Utils {
    //Some dummy data for the previews
    val dummyBreed = listOf(Breed(id = 1, "african", listOf(), listOf("https://images.dog.ceo/breeds/hound-afghan/n02088094_1145.jpg")))
    val dummyBreedFave = listOf(FavoriteDog(id = 1, "african", breedSubName = null, "https://images.dog.ceo/breeds/hound-afghan/n02088094_1145.jpg"))

    //Get network state
     val Context.checkNetwork: Boolean
        get() {
            val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return manager.getNetworkCapabilities(manager.activeNetwork)?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
}