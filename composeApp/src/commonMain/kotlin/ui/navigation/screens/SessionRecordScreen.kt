package ui.navigation.screens

import DrinkViewModel
import SessionRecordViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.components.NewDrinkEventForm
import ui.components.display.DrinkEventList


@Composable
fun SessionRecordScreen(toHomeScreen: () -> Unit, database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionRecordViewModel: SessionRecordViewModel = viewModel { SessionRecordViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    val drinkEvents by sessionRecordViewModel.drinkEvents.collectAsState()

    val session by sessionRecordViewModel.session.collectAsState()

    Column {
        Text("Session Drinks: ${drinkEvents.size}")

        NewDrinkEventForm(drinks, session!!) {
            sessionRecordViewModel.addDrinkEvent(it)
        }

        DrinkEventList(drinkEvents) {
            sessionRecordViewModel.deleteDrinkEvent(it)
        }

        Button(onClick = { toHomeScreen() }) {
            Text("End Session")
        }
    }
}