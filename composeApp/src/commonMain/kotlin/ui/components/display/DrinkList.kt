package ui.components.display

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.drink.Drink
import data.drink.DrinkType
import kotlinx.coroutines.launch
import ui.components.input.DrinkTypeSelectChips


@Composable
fun DrinkList(drinks: List<Drink>) {
    val orderedDrinks = drinks.sortedBy { it.name }
    var filteredDrinks by remember { mutableStateOf(orderedDrinks) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedDrinkType by remember { mutableStateOf<DrinkType?>(null) }

    // Map the Drinks to be Grouped by the First Letter of the Name and it's Index
    val drinkLetterMap = orderedDrinks.mapIndexed { index, drink ->
        drink.name.first().uppercaseChar() to index
    }.groupBy { it.first }
    val drinkLetters = drinkLetterMap.keys.sorted()

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    fun filterDrinks() {
        filteredDrinks = orderedDrinks
            .filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
            .filter { selectedDrinkType?.run { it.type == selectedDrinkType } ?: true }
    }

    Column {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                filterDrinks()
            },
            label = { Text("Search Drinks") },
            modifier = Modifier.fillMaxWidth()
        )

        DrinkTypeSelectChips { type ->
            selectedDrinkType = type
            filterDrinks()
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f)
            ) {
                items(filteredDrinks) {
                    DrinkCard(it)
                }
            }

            Column(modifier = Modifier.width(20.dp)) {
                drinkLetters.forEach { letter ->
                    Text(
                        text = letter.toString(),
                        modifier = Modifier.clickable {
                            drinkLetterMap[letter]?.first()?.let { (drinkNameLetter, index) ->
                                scope.launch {
                                    listState.animateScrollToItem(index)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
