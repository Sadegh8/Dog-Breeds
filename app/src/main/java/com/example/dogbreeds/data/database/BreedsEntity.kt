package com.example.dogbreeds.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogbreeds.domain.model.Breed

@Entity(tableName = "breeds_table")
data class BreedsEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val breedName: String = "",
    val breedSubNames: List<String> = listOf(),
)

fun BreedsEntity.toBreed(): Breed {
    return Breed(
        id = id,
        breedName = breedName,
        breedSubNames =  breedSubNames,
    )
}