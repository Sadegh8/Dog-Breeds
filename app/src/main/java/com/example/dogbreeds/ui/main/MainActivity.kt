package com.example.dogbreeds.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dogbreeds.ui.common.BottomNav
import com.example.dogbreeds.ui.common.TopBar
import com.example.dogbreeds.ui.navigation.AppNavigation
import com.example.dogbreeds.ui.navigation.Routes
import com.example.dogbreeds.ui.theme.DogBreedsTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberAnimatedNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }


                    // Control BottomBar
                    when (navBackStackEntry?.destination?.route) {
                        Routes.BREEDS, Routes.FAVORITE -> {
                            // Show BottomBar
                            bottomBarState.value = true
                        }
                        else -> {
                            // Hide BottomBar
                            bottomBarState.value = false
                        }
                    }


                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        bottomBar = {
                            BottomNav(navController = navController, bottomBarState)
                        },
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopBar(
                                Modifier,
                                navController,
                                bottomBarState
                            )
                        },

                        ) { padding ->

                        AppNavigation(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            padding = padding,
                            scaffoldState = scaffoldState,

                            )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogBreedsTheme {
        Text(text = "Dog breeds")
    }
}