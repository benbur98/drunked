package ui

import DrinkViewModel
import SessionViewModel
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
    val numberOfDrinks = drinkEvents.size

    Text("Session Drinks: $numberOfDrinks")

    NewDrinkEventForm(drinks, sessionViewModel.session) {
        sessionViewModel.addDrinkEvent(it)
    }

    Button(onClick = { println("Session Ended") }) {
        Text("End Session")
    }
}