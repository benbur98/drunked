package ui

import DrinkViewModel
import SessionViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ui.components.NewDrinkEventForm


@Composable
fun SessionRecordingPage(drinkViewModel: DrinkViewModel, sessionViewModel: SessionViewModel) {
    val drinks by drinkViewModel.drinks.collectAsState()

    val drinkEvents by sessionViewModel.drinkEvents.collectAsState()

    val session by sessionViewModel.session.collectAsState()

    Column {
        Text("Session Drinks: ${drinkEvents.size}")

        NewDrinkEventForm(drinks, session!!) {
            sessionViewModel.addDrinkEvent(it)
        }

        Button(onClick = {
            sessionViewModel.endSession()
        }) {
            Text("End Session")
        }
    }
}