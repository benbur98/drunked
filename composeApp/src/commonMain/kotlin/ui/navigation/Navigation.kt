package ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ui.navigation.screens.DrinksScreen
import ui.navigation.screens.HomeScreen
import ui.navigation.screens.SessionScreen


@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.fromRoute(backStackEntry?.destination?.route)

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screen.DrinksScreen.route) {
            DrinksScreen()
        }
        composable(route = Screen.SessionsScreen.route) {
            SessionScreen()
        }
    }
}