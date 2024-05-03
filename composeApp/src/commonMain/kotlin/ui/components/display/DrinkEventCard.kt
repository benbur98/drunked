package ui.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
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