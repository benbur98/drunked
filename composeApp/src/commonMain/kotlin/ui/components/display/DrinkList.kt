package ui.components.display

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import data.drink.Drink
import ui.components.input.DrinkTypeSelectChips


@Composable
fun DrinkList(drinks: List<Drink>) {
    var filteredDrinks by remember { mutableStateOf(drinks) }

    val drinkAlphabet: List<Char> = drinks.map { it.name.first() }.toSet().toList()
    
    val listState = rememberLazyListState()

    DrinkTypeSelectChips { type ->
        filteredDrinks = drinks.filter { it.type == type }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.weight(1f)
    ) {
        items(drinks) {
            DrinkCard(it)
        }
    }

    val offsets = remember { mutableStateMapOf<Int, Float>() }
    var selectedHeaderIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    fun updateSelectedIndexIfNeeded(offset: Float) {
        val index = offsets
            .mapValues { abs(it.value - offset) }
            .entries
            .minByOrNull { it.value }
            ?.key ?: return
        if (selectedHeaderIndex == index) return
        selectedHeaderIndex = index
        val selectedItemIndex =
            items.indexOfFirst { it.first().uppercase() == drinkAlphabet[selectedHeaderIndex] }
        scope.launch {
            listState.scrollToItem(selectedItemIndex)
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTapGestures {
                    updateSelectedIndexIfNeeded(it.y)
                }
            }
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, _ ->
                    updateSelectedIndexIfNeeded(change.position.y)
                }
            }
    ) {
        drinkAlphabet.forEachIndexed { i, letter ->
            Text(
                letter,
                modifier = Modifier.onGloballyPositioned {
                    offsets[i] = it.boundsInParent().center.y
                }
            )
        }
    }
}
