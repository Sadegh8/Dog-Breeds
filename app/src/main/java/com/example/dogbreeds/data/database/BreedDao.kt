package com.example.dogbreeds.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {

    @Query("SELECT * from breeds_table ORDER BY breedName ASC")
    suspend fun getBreeds(): List<BreedsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(breeds: List<BreedsEntity>)

}