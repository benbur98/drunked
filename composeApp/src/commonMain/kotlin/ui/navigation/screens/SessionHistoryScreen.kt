package ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.datasources.SessionDataSource
import org.koin.compose.koinInject
import ui.components.calendar.SessionCalendar
import ui.components.display.SessionList


@Composable
fun SessionHistoryScreen(toSessionOverviewScreen: (Int) -> Unit, database: DrunkedDatabase = koinInject()) {
    val pastSessions = SessionDataSource(database).getAllSessions()

    Column {
        SessionCalendar(pastSessions) {
            toSessionOverviewScreen(it.id!!)
        }
        SessionList(pastSessions) {
            toSessionOverviewScreen(it.id!!)
        }
    }
}