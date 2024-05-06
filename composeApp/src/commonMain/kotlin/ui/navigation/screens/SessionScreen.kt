package ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import ui.components.calendar.SessionCalendar
import ui.components.display.SessionDetail
import ui.components.display.SessionList


@Composable
fun SessionScreen(database: DrunkedDatabase) {
    val pastSessions = SessionDataSource(database).getAllSessions()
    var session: Session? by remember { mutableStateOf(null) }

    Column {
        Text("SESSIONS")

        SessionList(pastSessions)

        SessionCalendar(pastSessions) {
            session = it
        }

        if (session != null) {
            SessionDetail(session!!, DrinkEventDataSource(database))
        }
    }
}