package com.example.dogbreeds.ui.breedsList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BreedItem(
    modifier: Modifier = Modifier,
    title: String,
    subBreeds: List<String>,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                subBreeds.forEachIndexed { i, text ->
                    if (i < 5) {
                        val txt = if (i == 0) text else ", $text"
                        Text(text = txt)
                    } else if (i == 5) {
                        Text(text = ", ..")
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BreedItemPreview() {
    BreedItem(title = "australian", subBreeds = listOf("shepherd")) {}

}