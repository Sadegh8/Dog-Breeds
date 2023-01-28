package com.example.dogbreeds.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dogbreeds.data.converters.StringListConverter
import com.example.dogbreeds.data.database.favorite.FavoriteDao
import com.example.dogbreeds.data.database.favorite.FavoriteData

@Database(
    entities = [NetworkBreeds::class, FavoriteData::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class DogsDatabase: RoomDatabase() {
    abstract val breedsDao: BreedDao
    abstract val favoriteDao: FavoriteDao
}

