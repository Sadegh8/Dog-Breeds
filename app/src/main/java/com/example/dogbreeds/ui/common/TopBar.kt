package com.example.dogbreeds.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dogbreeds.R


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {

    TopAppBar(
        modifier = modifier,
        title =
        {
            Text(
                text = stringResource(R.string.app_name),
            )

        },
        navigationIcon = {
            if (!bottomBarState.value) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Close"
                    )
                }
            }
        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    )

}

