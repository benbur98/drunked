package ui.components.display

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.drink.DrinkEvent
import ui.components.generic.SwipeToDeleteContainer


@Composable
fun DrinkEventList(drinkEvents: List<DrinkEvent>, onDelete: ((DrinkEvent) -> Unit)? = null) {
    val sortedDrinkEvents = drinkEvents.sortedBy { it.timestamp }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(sortedDrinkEvents, key = { it.id!! }) { drinkEvent ->
            if (onDelete != null) {
                SwipeToDeleteContainer(
                    item = drinkEvent,
                    onDelete = { onDelete(it) }
                ) { DrinkEventCard(it) }
            } else {
                DrinkEventCard(drinkEvent)
            }
        }
    }
}
