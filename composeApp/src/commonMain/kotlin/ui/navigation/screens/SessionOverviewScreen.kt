package ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import org.koin.compose.koinInject
import ui.components.display.DrinkEventList
import ui.components.display.SessionCard


@Composable
fun SessionOverviewScreen(sessionId: Int, toSessionHistoryScreen: () -> Unit, database: DrunkedDatabase = koinInject()) {
    val sessionDataSource = SessionDataSource(database)
    val drinkEventDataSource = DrinkEventDataSource(database)

    val session = sessionDataSource.getSessionById(sessionId)
    val drinkEvents = drinkEventDataSource.getDrinkEventsForSession(sessionId)

    Column {
        Button(onClick = toSessionHistoryScreen) {
            Text("Session History")
        }
        
        SessionCard(session)

        DrinkEventList(drinkEvents)
    }
}