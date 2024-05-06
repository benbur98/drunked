package ui.navigation


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