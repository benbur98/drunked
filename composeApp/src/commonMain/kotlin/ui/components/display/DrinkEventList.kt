package ui.components.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.drink.DrinkEvent
import ui.components.generic.SwipeToDeleteContainer


@Composable
fun DrinkEventList(drinkEvents: List<DrinkEvent>, onDelete: ((DrinkEvent) -> Unit)? = null) {
    val sortedDrinkEvents = drinkEvents.sortedBy { it.timestamp }

    if (sortedDrinkEvents.isEmpty()) {
        Text("No Drink Events", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(sortedDrinkEvents, key = { it.id!! }) { drinkEvent ->
                if (onDelete != null) {
                    SwipeToDeleteContainer(
                        item = drinkEvent,
                        onDelete = { onDelete(it) }
                    ) {
                        DrinkEventItem(it)
                    }
                } else {
                    DrinkEventItem(drinkEvent)
                }
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun DrinkEventItem(drinkEvent: DrinkEvent) {
    val drinkEventTitle = "${drinkEvent.drink.name} [${drinkEvent.volume}]"

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).padding(start = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(drinkEventTitle, fontWeight = FontWeight.Bold)
        Text(drinkEvent.timestamp)
    }
}
