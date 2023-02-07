package com.example.dogbreeds.ui.breedsList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogbreeds.ui.breedsList.components.BreedItem
import com.example.dogbreeds.utils.Utils
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.dogbreeds.ui.breedsList.data.BreedsState
import com.example.dogbreeds.ui.common.UiEvent
import com.example.dogbreeds.ui.navigation.Routes


//Stateful version It helps us to show the preview and have a better testability
@Composable
fun BreedsScreen(
    modifier: Modifier = Modifier,
    viewModel: BreedsViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val state = viewModel.breedsState
    BreedsScreen(modifier = modifier, state = state, onNavigate = onNavigate)

}

//Stateless version
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedsScreen(
    modifier: Modifier = Modifier,
    state: BreedsState,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error.isNotEmpty() -> {
                Text(text = "Error in getting data!")
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = rememberLazyListState()) {
                    items(state.breeds, key = { it.id }) { breed ->
                        BreedItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(135.dp)
                                .animateItemPlacement(),
                            title = breed.breedName,
                            subBreeds = breed.breedSubNames,
                            onClick = {
                                onNavigate(
                                    UiEvent.Navigate(
                                        Routes.BREED_IMAGES + "?name=${breed.breedName},breedId=${breed.id}"
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BreedsScreenPreview() {
    BreedsScreen(state = BreedsState(breeds = Utils.dummyBreed), onNavigate = {})
}