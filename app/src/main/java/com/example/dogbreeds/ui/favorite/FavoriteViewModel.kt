package com.example.dogbreeds.ui.favorite


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreeds.data.database.DogsDatabase
import com.example.dogbreeds.data.database.favorite.toFavoriteDog
import com.example.dogbreeds.domain.model.FavoriteDog
import com.example.dogbreeds.domain.useCases.FavoriteUseCase
import com.example.dogbreeds.ui.favorite.data.FilterData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val database: DogsDatabase,
    private val favoriteUseCase: FavoriteUseCase,
) : ViewModel() {
    var breedsState by mutableStateOf(FavBreedsState())
        private set

    private var allFave = mutableListOf<FavoriteDog>()

    init {
        viewModelScope.launch {
            database.favoriteDao.getAllFavoriteDogs().collect { result ->
                if (result.isEmpty()) {
                    breedsState = FavBreedsState(isEmpty = false)
                } else {
                    allFave = result.map { it.toFavoriteDog() }.toMutableList()
                    breedsState = (FavBreedsState(
                        breeds = result.map { it.toFavoriteDog() },
                        isEmpty = false,
                        filterList = result.map { FilterData(it.favoriteBreedName) }.distinct()
                    ))
                }
            }
        }
    }

    /**
     * To filter favorite list we need to find all the checked items
     */
    fun filterList(name: String, check: Boolean) {
        breedsState = breedsState.copy(filterList = breedsState.filterList.map {
            if (it.item == name) it.copy(check = check) else it
        })

        val allChecked = mutableListOf<String>()
        breedsState.filterList.forEach {
            if (it.check) {
                allChecked.add(it.item)
            }else {
                allChecked.remove(it.item)
            }
        }
        breedsState = breedsState.copy(breeds = allFave.filter {
            allChecked.contains(it.breedName)
        })

    }

    fun saveOrRemoveFav(name: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.addOrRemoveFavoriteImages(name = name, url = url, subName = "")
        }
    }
}