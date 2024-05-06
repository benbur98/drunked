package ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import org.koin.compose.koinInject
import ui.components.display.DrinkEventList


@Composable
fun SessionOverviewScreen(
    sessionId: Int,
    toSessionHistoryScreen: () -> Unit,
    database: DrunkedDatabase = koinInject()
) {
    val sessionDataSource = SessionDataSource(database)
    val drinkEventDataSource = DrinkEventDataSource(database)

    val session = sessionDataSource.getSessionById(sessionId)
    val drinkEvents = drinkEventDataSource.getDrinkEventsForSession(sessionId)

    Column {
        IconButton(onClick = { toSessionHistoryScreen() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Session: ${session.date}", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            DrinkEventList(drinkEvents)
        }
    }
}