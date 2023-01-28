package com.example.dogbreeds.ui.breedPictures

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreeds.data.common.Resource
import com.example.dogbreeds.data.database.DogsDatabase
import com.example.dogbreeds.domain.model.Breed
import com.example.dogbreeds.domain.repository.BreedsRepository
import com.example.dogbreeds.domain.useCases.FavoriteUseCase
import com.example.dogbreeds.ui.breedPictures.data.BreedState
import com.example.dogbreeds.utils.Utils.checkNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BreedPicturesViewModel @Inject constructor(
    private val repository: BreedsRepository,
    private val context: Application,
    private val database: DogsDatabase,
    private val favoriteUseCase: FavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var breedState by mutableStateOf(BreedState())
        private set

    var name: String? = null
    var id: Long = 0L

    init {
        //Get BreedID and Name from nav arguments
        savedStateHandle.get<Long>("breedId")?.let {
            id = it
        }
        savedStateHandle.get<String>("name")?.let {
            name = it
        }

        //Observer both getAllFavoriteDogs and getImages to populate the state with images and favorites state
        name?.let {
            database.favoriteDao.getAllFavoriteDogs()
                .combine(getImages(name = it, id = id)) { favorites, result ->
                    breedState = when (result) {
                        is Resource.Success -> {
                            (BreedState(breeds = result.data?.copy(favUrls = favorites.map { fave -> fave.favoriteUrls })))
                        }
                        is Resource.Error -> {
                            (BreedState(
                                error = result.message ?: "An unexpected error occurred"
                            ))
                        }
                        is Resource.Loading -> {
                            breedState.copy(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun saveOrRemoveFav(name: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.addOrRemoveFavoriteImages(name = name, url = url, subName = "")
        }
    }

    //Get specific breed images from API
    private fun getImages(name: String, id: Long): Flow<Resource<Breed>> = flow {
        var breedsImage = repository.getBreedImages(name = name, id = id)
        if (breedsImage.imageUrls.isEmpty()) {
            emit(Resource.Loading())
            try {
                val isNetworkConnected = context.checkNetwork
                if (isNetworkConnected) {
                    emit(Resource.Loading())
                    repository.getBreedImages(name = name, id = id)
                    breedsImage = repository.getBreedImages(name = name, id = id)
                    if (breedsImage.imageUrls.isEmpty()) {
                        emit(Resource.Success(breedsImage))
                    }
                }

            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error!"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }

        } else {
            emit(Resource.Success(breedsImage))
        }

    }
}