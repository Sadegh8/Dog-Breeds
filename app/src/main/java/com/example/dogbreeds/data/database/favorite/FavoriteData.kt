package com.example.dogbreeds.data.database.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogbreeds.domain.model.FavoriteDog

@Entity(tableName = "favorite_table")
data class FavoriteData constructor(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val favoriteBreedName: String = "",
    val favoriteBreedSubName: String?,
    val favoriteUrls: String
)

fun FavoriteData.toFavoriteDog(): FavoriteDog {
    return FavoriteDog(
        id = id,
        breedName = favoriteBreedName,
        breedSubName = "",
        favoriteImageUrl = favoriteUrls
    )
}
