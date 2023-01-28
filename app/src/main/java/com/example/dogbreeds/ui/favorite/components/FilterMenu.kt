package com.example.dogbreeds.ui.favorite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogbreeds.R
import com.example.dogbreeds.ui.favorite.data.FilterData

@Composable
fun FilterMenu(
    modifier: Modifier = Modifier, items: List<FilterData>,
    expandedDefault: Boolean = false,
    onItemClick: (Boolean, String) -> Unit
) {
    var expanded by rememberSaveable(expandedDefault) {
        mutableStateOf(expandedDefault)
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(2.dp),
        elevation = 0.dp
    ) {
        Card(modifier = Modifier
            .padding(16.dp)
            .clickable {
                expanded = !expanded
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(10.dp)
            )
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Text(text = "Filter")

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter"
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .requiredSizeIn(maxHeight = screenHeight / 2)
                .background(
                    MaterialTheme.colors.surface
                )


        ) {
            items.forEach { data ->
                DropdownMenuItem(onClick = {
                    onItemClick(!data.check, data.item)
                }) {

                    Checkbox(
                        modifier = Modifier.padding(4.dp),
                        checked = data.check,
                        onCheckedChange = {
                            onItemClick(!data.check, data.item)
                        })

                    Spacer(modifier = Modifier.weight(1f))

                    Text(modifier = Modifier.padding(4.dp), text = data.item)
                }
            }
        }
    }

}


@Preview
@Composable
fun FilterMenuPreview() {
    FilterMenu(items = listOf(), onItemClick = { _, _ -> })
}