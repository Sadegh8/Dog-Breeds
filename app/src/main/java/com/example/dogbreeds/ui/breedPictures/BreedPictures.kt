package com.example.dogbreeds.ui.breedPictures


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogbreeds.ui.breedPictures.data.BreedState
import com.example.dogbreeds.ui.common.BreedImgItem
import com.example.dogbreeds.utils.Utils

@Composable
fun BreedPictures(viewModel: BreedPicturesViewModel = hiltViewModel()) {
    val state = viewModel.breedState
    BreedPictures(state, onTapItem = { name, url ->
        viewModel.saveOrRemoveFav(name = name, url = url)
    })
}

@Composable
fun BreedPictures(
    state: BreedState,
    onTapItem: (String, String) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error.isNotEmpty()) {
            Text(text = "Error in getting images!")
        } else {
            state.breeds?.let { breed ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxSize(),
                    columns = GridCells.Adaptive(130.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                ) {
                    items(breed.imageUrls.size) { item ->
                        BreedImgItem(
                            url = breed.imageUrls[item],
                            name = breed.breedName,
                            favorite = breed.favUrls.find { it == breed.imageUrls[item] }
                                ?.isNotEmpty() ?: false,
                            onTapItem = {
                                onTapItem(breed.breedName, breed.imageUrls[item])
                            })
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BreedPicturesPreview() {
    BreedPictures(state = BreedState(breeds = Utils.dummyBreed.first()),
        onTapItem = { _, _ -> })
}