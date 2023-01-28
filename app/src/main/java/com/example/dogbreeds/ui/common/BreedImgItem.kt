package com.example.dogbreeds.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dogbreeds.ui.theme.Yellow

@Composable
fun BreedImgItem(
    modifier: Modifier = Modifier,
    name: String,
    url: String? = null,
    favorite: Boolean = false,
    onTapItem: () -> Unit
) {

    Card(modifier = modifier
        .padding(8.dp)
        .clickable {
            onTapItem()
        }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //Use coil to load images
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)

                Spacer(modifier = Modifier.weight(1f))

                //Favorite icon
                Image(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    colorFilter = if (favorite) ColorFilter.tint(Yellow) else ColorFilter.tint(
                        MaterialTheme.colors.onBackground
                    )
                )

            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BreedImgItemPreview() {
    BreedImgItem(
        name = "hound",
        url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
    ) {}
}