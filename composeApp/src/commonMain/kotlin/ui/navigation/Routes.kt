package ui.navigation

import androidx.navigation.NavController


enum class ScreenRoute {
    HOME, DRINKS, SESSIONS;

    companion object {
        fun fromRoute(route: String?): ScreenRoute = enumValueOf(route?.uppercase() ?: HOME.name)
    }
}


sealed class Screen(val route: String) {
    object HomeScreen : Screen(ScreenRoute.HOME.name)
    object DrinksScreen : Screen(ScreenRoute.DRINKS.name)
    object SessionsScreen : Screen(ScreenRoute.SESSIONS.name)

    companion object {
        fun fromRoute(route: String?): Screen = when (ScreenRoute.fromRoute(route)) {
            ScreenRoute.HOME -> HomeScreen
            ScreenRoute.DRINKS -> DrinksScreen
            ScreenRoute.SESSIONS -> SessionsScreen
        }
    }
}


fun NavController.getCurrentScreen(): Screen {
    val navBackStackEntry = this.currentBackStackEntry
    return Screen.fromRoute(navBackStackEntry?.destination?.route)
}

//fun NavController.navigateTo(screen: Screen) {
//    this.navigate(screen.route) {
//        this.graph.startDestinationRoute?.let {
//            popUpTo(it) {
//                saveState = true
//            }
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
//}