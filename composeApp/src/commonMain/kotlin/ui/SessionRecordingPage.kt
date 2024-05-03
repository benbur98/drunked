package ui

import DrinkViewModel
import SessionViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.components.NewDrinkEventForm


@Composable
fun SessionRecordingPage(drinkViewModel: DrinkViewModel, sessionViewModel: SessionViewModel) {
    val drinks by drinkViewModel.drinks.collectAsState()

    val drinkEvents by sessionViewModel.drinkEvents.collectAsState()
    val numberOfDrinks = drinkEvents.size

    val session by sessionViewModel.session.collectAsState()

    Card(
        modifier = Modifier.padding(4.dp).border(1.dp, color = Color.Black)
    ) {
        Column {
            Text("Session Drinks: $numberOfDrinks")

            NewDrinkEventForm(drinks, session!!) {
                sessionViewModel.addDrinkEvent(it)
            }

            Button(onClick = { println("Session Ended") }) {
                Text("End Session")
            }
        }
    }
}