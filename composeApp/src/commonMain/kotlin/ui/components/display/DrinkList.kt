package ui.components.display

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import data.drink.Drink
import data.drink.DrinkType
import data.drink.toPercentageString
import kotlinx.coroutines.launch
import ui.components.input.SearchInput
import ui.components.select.DrinkSortSelect
import ui.components.select.DrinkTypeSelectChips
import ui.components.select.SortType


@Composable
fun DrinkList(drinks: List<Drink>) {
    val orderedDrinks = drinks.sortedBy { it.name }

    var filteredDrinks by remember { mutableStateOf(orderedDrinks) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedDrinkTypes: List<DrinkType>? by remember { mutableStateOf(null) }
    var sortType: SortType? by remember { mutableStateOf(null) }

    // Map the Drinks to be Grouped by the First Letter of the Name and it's Index
    var drinkLetterMap by remember {
        mutableStateOf(orderedDrinks.mapIndexed { index, drink ->
            drink.name.first().uppercaseChar() to index
        }.groupBy { it.first })
    }
    var drinkLetters by remember { mutableStateOf(drinkLetterMap.keys.sorted()) }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    fun filterDrinks() {
        filteredDrinks = orderedDrinks
            .filter { it.name.contains(searchQuery, ignoreCase = true) }
            .filter {
                if (selectedDrinkTypes.isNullOrEmpty()) return@filter true
                it.type in selectedDrinkTypes!!
            }
            .let { drinks ->
                when (sortType) {
                    SortType.NAME_ASC -> drinks.sortedBy { it.name }
                    SortType.NAME_DESC -> drinks.sortedByDescending { it.name }
                    SortType.ABV_ASC -> drinks.sortedBy { it.abv }
                    SortType.ABV_DESC -> drinks.sortedByDescending { it.abv }
                    null -> drinks
                }
            }
        drinkLetterMap = filteredDrinks.mapIndexed { index, drink ->
            drink.name.first().uppercaseChar() to index
        }.groupBy { it.first }
        drinkLetters = drinkLetterMap.keys.sorted()
    }

    LaunchedEffect(drinks) {
        filterDrinks()
    }

    Column {
        SearchInput {
            searchQuery = it
            filterDrinks()
        }

        DrinkTypeSelectChips {
            selectedDrinkTypes = it
            filterDrinks()
        }

        DrinkSortSelect {
            sortType = it
            filterDrinks()
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier.align(Alignment.Top).fillMaxHeight().fillMaxWidth().weight(1f)
            ) {
                items(filteredDrinks) { drink ->
                    DrinkItem(drink)
                    HorizontalDivider()
                }
            }

            Column(modifier = Modifier.width(20.dp).zIndex(1f)) {
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


@Composable
fun DrinkItem(drink: Drink) {
    val drinkTitle = "${drink.name} [${drink.type.text}]"

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(drinkTitle, fontWeight = FontWeight.Bold)
        Text(drink.abv.toPercentageString())
    }
}
