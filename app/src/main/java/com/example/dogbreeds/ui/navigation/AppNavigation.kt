package com.example.dogbreeds.ui.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import com.example.dogbreeds.ui.breedPictures.BreedPictures
import com.example.dogbreeds.ui.breedsList.BreedsScreen
import com.example.dogbreeds.ui.common.BottomNavItem
import com.example.dogbreeds.ui.favorite.FavoriteScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    padding: PaddingValues,
    scaffoldState: ScaffoldState
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.BREEDS,
        enterTransition = { defaultEarthquakeEnterTransition(initialState) },
        exitTransition = { defaultEarthquakeExitTransition(targetState) },
        popEnterTransition = { defaultEarthquakePopEnterTransition(initialState) },
        popExitTransition = { defaultEarthquakePopExitTransition(targetState) },
        modifier = modifier
    ) {
        addBreed(
            navController,
            padding = padding
        )
        addFavorites(padding = padding, scaffoldState = scaffoldState)
        addImages()
    }
}


@ExperimentalAnimationApi
private fun NavGraphBuilder.addBreed(
    navController: NavController,
    padding: PaddingValues,
) {
    composable(
        route = Routes.BREEDS
    )
    {
        BreedsScreen(
            modifier = Modifier.padding(padding)
        ) {
            navController.navigate(it.route)
        }
    }

}


@ExperimentalAnimationApi
private fun NavGraphBuilder.addFavorites(
    padding: PaddingValues,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = BottomNavItem.Map.screen_route
    )
    {
        FavoriteScreen(
            modifier = Modifier.padding(padding),
            scaffoldState = scaffoldState,
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addImages() {
    composable(
        route = Routes.BREED_IMAGES + "?name={name},breedId={breedId}",
        arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            },
            navArgument("breedId") {
                type = NavType.LongType
            }
        )
    )
    {
       BreedPictures()
    }
}


@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultEarthquakeEnterTransition(
    initial: NavBackStackEntry
): EnterTransition {
    val initialNavGraph = initial.destination
    if (initialNavGraph.route == Routes.BREEDS || initialNavGraph.route == Routes.FAVORITE) {
        return fadeIn()
    }
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultEarthquakeExitTransition(
    target: NavBackStackEntry
): ExitTransition {
    val targetNavGraph = target.destination
    if (targetNavGraph.route == Routes.BREEDS || targetNavGraph.route == Routes.FAVORITE) {
        return fadeOut()
    }
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultEarthquakePopEnterTransition(
    initial: NavBackStackEntry
): EnterTransition {
    val initialNavGraph = initial.destination
    if (initialNavGraph.route == Routes.BREEDS || initialNavGraph.route == Routes.FAVORITE) {
        return fadeIn()
    }
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.End)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultEarthquakePopExitTransition(
    target: NavBackStackEntry
): ExitTransition {
    val targetNavGraph = target.destination
    if (targetNavGraph.route == Routes.BREEDS || targetNavGraph.route == Routes.FAVORITE) {
        return fadeOut()
    }
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.End)
}