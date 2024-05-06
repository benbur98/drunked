package ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.navigation.screens.DrinksScreen
import ui.navigation.screens.HomeScreen
import ui.navigation.screens.SessionHistoryScreen
import ui.navigation.screens.SessionRecordScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screen.HomeScreen.route) {
            val toSessionRecordScreen = { navController.navigate(Screen.SessionRecordScreen.route) }
            HomeScreen(toSessionRecordScreen)
        }
        composable(route = Screen.DrinksScreen.route) {
            DrinksScreen()
        }
        composable(route = Screen.SessionHistoryScreen.route) {
            SessionHistoryScreen()
        }
        composable(route = Screen.SessionRecordScreen.route) {
            val toHomeScreen = { navController.navigate(Screen.HomeScreen.route) }
            SessionRecordScreen(toHomeScreen)
        }
    }
}