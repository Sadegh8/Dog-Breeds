package com.example.dogbreeds.ui.breedPictures.data

import com.example.dogbreeds.domain.model.Breed


data class BreedState(
    val isLoading: Boolean = false,
    val breeds: Breed? = null,
    val error: String = ""
)

