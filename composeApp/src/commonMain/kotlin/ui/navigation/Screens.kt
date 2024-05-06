package ui.navigation


enum class ScreenRoute {
    HOME, DRINKS, SESSION_HISTORY, SESSION_RECORD;

    companion object {
        fun fromRoute(route: String?): ScreenRoute = enumValueOf(route?.uppercase() ?: HOME.name)
    }
}


sealed class Screen(val route: String) {
    object HomeScreen : Screen(ScreenRoute.HOME.name)
    object DrinksScreen : Screen(ScreenRoute.DRINKS.name)
    object SessionHistoryScreen : Screen(ScreenRoute.SESSION_HISTORY.name)
    object SessionRecordScreen : Screen(ScreenRoute.SESSION_RECORD.name)

    companion object {
        fun fromRoute(route: String?): Screen = when (ScreenRoute.fromRoute(route)) {
            ScreenRoute.HOME -> HomeScreen
            ScreenRoute.DRINKS -> DrinksScreen
            ScreenRoute.SESSION_HISTORY -> SessionHistoryScreen
            ScreenRoute.SESSION_RECORD -> SessionRecordScreen
        }
    }
}
