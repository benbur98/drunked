package ui.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.drink.Drink
import data.drink.DrinkType
import ui.components.input.DrinkTypeSelectChips


@Composable
fun DrinkList(drinks: List<Drink>) {
    var filteredDrinks by remember { mutableStateOf(drinks) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedDrinkType by remember { mutableStateOf<DrinkType?>(null) }

    val drinkAlphabet: List<Char> = drinks.map { it.name.first() }.toSet().toList()

    fun filterDrinks() {
        filteredDrinks = drinks
            .filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
            .filter { selectedDrinkType?.run { it.type == selectedDrinkType } ?: true }
    }

    val listState = rememberLazyListState()

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

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            items(filteredDrinks) {
                DrinkCard(it)
            }
        }
//
//        val offsets = remember { mutableStateMapOf<Int, Float>() }
//        var selectedHeaderIndex by remember { mutableStateOf(0) }
//        val scope = rememberCoroutineScope()
//
//        fun updateSelectedIndexIfNeeded(offset: Float) {
//            val index = offsets
//                .mapValues { abs(it.value - offset) }
//                .entries
//                .minByOrNull { it.value }
//                ?.key ?: return
//            if (selectedHeaderIndex == index) return
//            selectedHeaderIndex = index
//            val selectedItemIndex = items.indexOfFirst { it.first().uppercase() == drinkAlphabet[selectedHeaderIndex] }
//            scope.launch {
//                listState.scrollToItem(selectedItemIndex)
//            }
//        }
//
//        Column(
//            verticalArrangement = Arrangement.SpaceEvenly,
//            modifier = Modifier
//                .fillMaxHeight()
//                .background(Color.Gray)
//                .pointerInput(Unit) {
//                    detectTapGestures {
//                        updateSelectedIndexIfNeeded(it.y)
//                    }
//                }
//                .pointerInput(Unit) {
//                    detectVerticalDragGestures { change, _ ->
//                        updateSelectedIndexIfNeeded(change.position.y)
//                    }
//                }
//        ) {
//            drinkAlphabet.forEachIndexed { i, letter ->
//                Text(
//                    letter.toString(),
//                    modifier = Modifier.onGloballyPositioned {
//                        offsets[i] = it.boundsInParent().center.y
//                    }
//                )
//            }
//        }
    }
}
