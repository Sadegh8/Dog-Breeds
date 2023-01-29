package com.example.dogbreeds.data.remote

import com.example.dogbreeds.data.remote.dto.BreedsData
import com.example.dogbreeds.data.remote.dto.BreedsImgData
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/all")
    suspend fun getListAllBreeds(): BreedsData

    @GET("breed/{breed_name}/images")
    suspend fun getBreedByName(@Path("breed_name") breedName: String): BreedsImgData
}