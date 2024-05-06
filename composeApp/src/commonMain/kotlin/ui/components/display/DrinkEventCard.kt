package ui.components.display

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.DrinkEvent


@Composable
fun DrinkEventCard(drinkEvent: DrinkEvent) {
    Card(modifier = Modifier.padding(4.dp).border(1.dp, color = Color.Black).fillMaxWidth()) {
        Column {
            Text(drinkEvent.timestamp)
            Text(drinkEvent.drink.name)
            Text(drinkEvent.volume.toString())
            Text(drinkEvent.units.toString())
        }
    }
}