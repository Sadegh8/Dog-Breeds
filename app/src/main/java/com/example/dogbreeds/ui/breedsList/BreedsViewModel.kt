package com.example.dogbreeds.ui.breedsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreeds.data.common.Resource
import com.example.dogbreeds.domain.useCases.GetAllBreedsUseCase
import com.example.dogbreeds.ui.breedsList.data.BreedsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {
    var breedsState by mutableStateOf(BreedsState())
        private set

    init {
        getAllBreedsUseCase().onEach { result ->
            breedsState = when (result) {
                is Resource.Success -> {
                    (BreedsState(breeds = result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    (BreedsState(
                        error = result.message ?: "An unexpected error occurred"
                    ))
                }
                is Resource.Loading -> {
                    breedsState.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}