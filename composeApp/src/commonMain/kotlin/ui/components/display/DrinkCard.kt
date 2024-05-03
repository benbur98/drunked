package ui.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import data.drink.Drink
import data.drink.toPercentageString


@Composable
fun DrinkCard(drink: Drink) {
    Card {
        Column {
            Text(drink.name)
            Text(drink.abv.toPercentageString())
            Text(drink.type.text)
        }
    }
}