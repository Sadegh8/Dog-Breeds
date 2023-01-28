package com.example.dogbreeds.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dogbreeds.ui.navigation.Routes
import com.example.dogbreeds.R

sealed class BottomNavItem(var title: Int, var icon: ImageVector, var screen_route: String) {
    object Main : BottomNavItem(R.string.breeds, Icons.Default.Home, Routes.BREEDS)
    object Map: BottomNavItem(R.string.favorite,Icons.Default.Star, Routes.FAVORITE)
}

