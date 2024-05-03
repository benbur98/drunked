package ui.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import data.drink.DrinkEvent


@Composable
fun DrinkEventCard(drinkEvent: DrinkEvent) {
    Card {
        Column {
            Text(drinkEvent.timestamp)
            Text(drinkEvent.drink.name)
            Text(drinkEvent.volume.toString())
            Text(drinkEvent.units.toString())
        }
    }
}