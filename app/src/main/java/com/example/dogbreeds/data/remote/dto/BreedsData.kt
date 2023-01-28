package com.example.dogbreeds.data.remote.dto

import com.example.dogbreeds.data.database.NetworkBreeds

data class BreedsData(
    val message: Map<String, List<String>>
)

fun BreedsData.toNetworkBreeds(): List<NetworkBreeds> {
    return this.message.map {
        NetworkBreeds(
            breedName = it.key,
            breedSubNames = it.value
        )
    }
}