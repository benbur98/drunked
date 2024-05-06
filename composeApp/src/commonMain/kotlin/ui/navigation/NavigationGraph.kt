package ui.navigation

import DatabaseViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.navigation.screens.DrinksScreen
import ui.navigation.screens.HomeScreen
import ui.navigation.screens.SessionScreen


@Composable
fun NavigationGraph(navController: NavHostController, databaseViewModel: DatabaseViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(databaseViewModel.database)
        }
        composable(route = Screen.DrinksScreen.route) {
            DrinksScreen(databaseViewModel.database)
        }
        composable(route = Screen.SessionsScreen.route) {
            SessionScreen(databaseViewModel.database)
        }
    }
}