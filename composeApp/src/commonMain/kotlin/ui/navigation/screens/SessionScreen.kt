package ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import org.koin.compose.koinInject
import ui.components.calendar.SessionCalendar
import ui.components.display.SessionDetail
import ui.components.display.SessionList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(database: DrunkedDatabase = koinInject()) {
    val pastSessions = SessionDataSource(database).getAllSessions()
    var session: Session? by remember { mutableStateOf(null) }

    Column {
        SessionCalendar(pastSessions) {
            session = it
        }
        SessionList(pastSessions) {
            session = it
        }

        if (session != null) {
            ModalBottomSheet(
                onDismissRequest = { session = null },
                sheetState = rememberModalBottomSheetState(),
            ) {
                SessionDetail(session!!, DrinkEventDataSource(database))
            }
        }
    }
}