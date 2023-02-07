package com.example.dogbreeds.data.remote.dto

import com.example.dogbreeds.data.database.BreedsEntity

data class BreedsData(
    val message: Map<String, List<String>>
)

fun BreedsData.toBreedsEntity(): List<BreedsEntity> {
    return this.message.map {
        BreedsEntity(
            breedName = it.key,
            breedSubNames = it.value
        )
    }
}