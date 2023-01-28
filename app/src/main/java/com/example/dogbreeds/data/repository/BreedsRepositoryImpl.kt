package com.example.dogbreeds.data.repository

import android.util.Log
import com.example.dogbreeds.data.database.DogsDatabase
import com.example.dogbreeds.data.database.favorite.FavoriteData
import com.example.dogbreeds.data.database.favorite.toFavoriteDog
import com.example.dogbreeds.data.database.toBreed
import com.example.dogbreeds.data.remote.DogApi
import com.example.dogbreeds.data.remote.dto.toNetworkBreeds
import com.example.dogbreeds.domain.model.Breed
import com.example.dogbreeds.domain.model.FavoriteDog
import com.example.dogbreeds.domain.repository.BreedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedsRepositoryImpl
@Inject constructor(
    private val api: DogApi,
    private val database: DogsDatabase
) : BreedsRepository {

    override suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val breedsList = api.getListAllBreeds()
            database.breedsDao.insertAll(breedsList.toNetworkBreeds())
        }
    }

    override suspend fun getAllBreeds(): List<Breed> {
        return database.breedsDao.getBreeds().map { it.toBreed() }
    }

    override suspend fun getBreedImages(name: String, id: Long): Breed {
        return Breed(breedName = name, imageUrls = api.getBreedByName(name).message, id = id)
    }

}