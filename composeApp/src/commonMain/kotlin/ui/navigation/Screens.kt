package ui.navigation


enum class ScreenRoute {
    HOME, DRINKS, SESSION_HISTORY, SESSION_OVERVIEW, SESSION_RECORD, UNKNOWN;

    companion object {
        fun fromRoute(route: String?): ScreenRoute = try {
            enumValueOf(route?.uppercase() ?: HOME.name)
        } catch (_: IllegalArgumentException) {
            UNKNOWN
        }
    }
}


sealed class Screen(val route: String) {
    object HomeScreen : Screen(ScreenRoute.HOME.name)
    object DrinksScreen : Screen(ScreenRoute.DRINKS.name)
    object SessionHistoryScreen : Screen(ScreenRoute.SESSION_HISTORY.name)
    object SessionOverviewScreen : Screen(ScreenRoute.SESSION_OVERVIEW.name)
    object SessionRecordScreen : Screen(ScreenRoute.SESSION_RECORD.name)
    object UnknownScreen : Screen(ScreenRoute.UNKNOWN.name)

    companion object {
        fun fromRoute(route: String?): Screen = when (ScreenRoute.fromRoute(route)) {
            ScreenRoute.HOME -> HomeScreen
            ScreenRoute.DRINKS -> DrinksScreen
            ScreenRoute.SESSION_HISTORY -> SessionHistoryScreen
            ScreenRoute.SESSION_OVERVIEW -> SessionOverviewScreen
            ScreenRoute.SESSION_RECORD -> SessionRecordScreen
            ScreenRoute.UNKNOWN -> UnknownScreen
        }
    }
}
