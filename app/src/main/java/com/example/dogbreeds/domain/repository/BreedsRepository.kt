package com.example.dogbreeds.domain.repository

import com.example.dogbreeds.data.database.favorite.FavoriteData
import com.example.dogbreeds.domain.model.Breed
import com.example.dogbreeds.domain.model.FavoriteDog
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {
    suspend fun refreshList()
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getBreedImages(name: String, id: Long): Breed

}