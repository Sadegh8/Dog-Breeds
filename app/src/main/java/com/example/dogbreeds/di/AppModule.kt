package com.example.dogbreeds.di

import android.app.Application
import androidx.room.Room
import com.example.dogbreeds.data.common.Constants
import com.example.dogbreeds.data.common.Constants.DATABASE_NAME
import com.example.dogbreeds.data.database.DogsDatabase
import com.example.dogbreeds.data.remote.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Create Retrofit to get breeds list and dogs images
    @Provides
    @Singleton
    fun provideStockApi(): DogApi {
        val clientSetup = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientSetup)
            .build()
            .create(DogApi::class.java)
    }

    //Build the two table room database to cash breeds list and save favorite dog
    @Provides
    @Singleton
    fun provideSingletonBreedsDatabase(app: Application): DogsDatabase {
        return Room.databaseBuilder(
            app,
            DogsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    }
}