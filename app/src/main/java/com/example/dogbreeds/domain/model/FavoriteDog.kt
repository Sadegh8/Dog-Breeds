package com.example.dogbreeds.domain.model

data class FavoriteDog(
    val id: Long,
    val breedName: String = "",
    val breedSubName: String? = "",
    val favoriteImageUrl: String
)
