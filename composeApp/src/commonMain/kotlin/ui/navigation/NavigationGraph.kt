package ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ui.navigation.screens.DrinksScreen
import ui.navigation.screens.HomeScreen
import ui.navigation.screens.SessionHistoryScreen
import ui.navigation.screens.SessionOverviewScreen
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
            val toSessionOverviewScreen = { sessionId: Int ->
                navController.navigate("${Screen.SessionOverviewScreen.route}/$sessionId")
            }
            SessionHistoryScreen(toSessionOverviewScreen)
        }
        composable(
            route = "${Screen.SessionOverviewScreen.route}/{sessionId}",
            arguments = listOf(
                navArgument("sessionId") { type = NavType.IntType }
            )
        ) {
            val toSessionHistoryScreen = { navController.navigate(Screen.SessionHistoryScreen.route) }
            val sessionId = it.arguments?.getInt("sessionId")
            if (sessionId != null) SessionOverviewScreen(sessionId, toSessionHistoryScreen)
        }
        composable(route = Screen.SessionRecordScreen.route) {
            val toHomeScreen = { navController.navigate(Screen.HomeScreen.route) }
            SessionRecordScreen(toHomeScreen)
        }
    }
}