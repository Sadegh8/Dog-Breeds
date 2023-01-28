package com.example.dogbreeds.ui.breedsList.data

import com.example.dogbreeds.domain.model.Breed


data class BreedsState(
    val isLoading: Boolean = false,
    val breeds: List<Breed> = emptyList(),
    val error: String = ""
)

