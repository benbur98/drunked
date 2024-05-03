package ui.components.display

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import data.drink.Drink


@Composable
fun DrinkList(drinks: List<Drink>) {
    LazyColumn {
        items(drinks) { drink ->
            DrinkCard(drink)
        }
    }
}