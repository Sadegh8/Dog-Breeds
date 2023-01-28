package com.example.dogbreeds.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogbreeds.ui.common.BreedImgItem
import com.example.dogbreeds.ui.favorite.components.FilterMenu
import com.example.dogbreeds.utils.Utils


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val state = viewModel.breedsState
    FavoriteScreen(
        modifier = modifier,
        state = state,
        scaffoldState = scaffoldState,
        onTapItem = { name, url ->
            viewModel.saveOrRemoveFav(name = name, url = url)
        }, onFilterTap = { check, item ->
            viewModel.filterList(name = item, check = check)

        })

}

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    state: FavBreedsState,
    scaffoldState: ScaffoldState? = null,
    onTapItem: (String, String) -> Unit,
    onFilterTap: (Boolean, String) -> Unit
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isEmpty) {
            Text(text = "There is nothing here!")
        } else {
            state.breeds.let { breed ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxSize(),
                    columns = GridCells.Adaptive(130.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                ) {
                    item(span = { GridItemSpan(this.maxLineSpan) }, content = {
                        FilterMenu(
                            modifier = Modifier
                                .padding(16.dp),
                            items = state.filterList,
                            onItemClick = { check, item ->
                                onFilterTap(check, item)
                            })
                    })

                    items(breed.size, key = { breed[it].favoriteImageUrl }) { item ->
                        BreedImgItem(
                            url = breed[item].favoriteImageUrl,
                            name = breed[item].breedName,
                            favorite = true,
                            onTapItem = {
                                onTapItem(
                                    breed[item].breedName,
                                    breed[item].favoriteImageUrl
                                )
                            })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreen() {
    FavoriteScreen(
        state = FavBreedsState(breeds = Utils.dummyBreedFave, isEmpty = false),
        onTapItem = { _, _ -> },
        onFilterTap = { _, _ -> })
}