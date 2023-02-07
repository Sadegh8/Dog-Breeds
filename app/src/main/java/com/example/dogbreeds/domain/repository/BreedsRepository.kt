package com.example.dogbreeds.domain.repository

import com.example.dogbreeds.domain.model.Breed

interface BreedsRepository {
    suspend fun refreshList()
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getBreedImages(name: String, id: Long): Breed

}