package com.example.dogbreeds.domain.model

data class Breed(
    val id: Long,
    val breedName: String = "",
    val breedSubNames: List<String> = listOf(),
    val imageUrls: List<String> = listOf(),
    val favUrls: List<String> = listOf(),
)
