package com.example.dogbreeds.data.database.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * from favorite_table ORDER BY favoriteBreedName ASC")
    fun getAllFavoriteDogs(): Flow<List<FavoriteData>>

    @Query("SELECT * from favorite_table WHERE favoriteUrls = :url")
    suspend fun getFavoriteDogsByUrl(url: String): FavoriteData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(breeds: FavoriteData)

    @Query("DELETE FROM favorite_table WHERE favoriteUrls = :url")
    suspend fun deleteItem(url: String)
}