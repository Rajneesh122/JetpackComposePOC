package com.jetpack.composepoc.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.composepoc.ui.screens.profile.UserProfileScreen
import com.jetpack.composepoc.ui.screens.search.SearchScreen
import com.jetpack.composepoc.utils.EMPTY_STRING
import com.jetpack.composepoc.utils.SEARCH_SCREEN
import com.jetpack.composepoc.utils.USER_PROFILE

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = SEARCH_SCREEN) {
        composable(SEARCH_SCREEN) {
            SearchScreen(navController)
        }
        composable("$USER_PROFILE/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: EMPTY_STRING
            UserProfileScreen(username)
        }
    }
}

