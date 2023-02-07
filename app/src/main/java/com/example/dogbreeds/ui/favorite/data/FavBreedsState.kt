package com.example.dogbreeds.ui.favorite.data

import com.example.dogbreeds.domain.model.FavoriteDog
import com.example.dogbreeds.ui.favorite.data.FilterData


data class FavBreedsState(
    val isEmpty: Boolean = true,
    val breeds: List<FavoriteDog> = emptyList(),
    val filterList: List<FilterData> = emptyList(),
)

