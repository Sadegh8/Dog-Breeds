package com.example.dogbreeds.domain.useCases

import com.example.dogbreeds.data.database.DogsDatabase
import com.example.dogbreeds.data.database.favorite.FavoriteData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Save or remove the dog from the favorites table
 */
class FavoriteUseCase @Inject constructor(
    private val database: DogsDatabase
) {

    suspend fun addOrRemoveFavoriteImages(
        name: String,
        subName: String?,
        url: String
    ) {
        withContext(Dispatchers.IO) {
            val result = database.favoriteDao.getFavoriteDogsByUrl(url = url)
            if (result == null) {
                database.favoriteDao.insertDog(
                    FavoriteData(
                        favoriteBreedName = name,
                        favoriteUrls = url,
                        favoriteBreedSubName = subName
                    )
                )
            } else {
                database.favoriteDao.deleteItem(url)
            }
        }
    }
}