package ui.components.input

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.drink.DrinkType


@Composable
fun DrinkTypeSelectChips(onSelect: (DrinkType?) -> Unit) {
    var selectedDrinkType by remember { mutableStateOf<DrinkType?>(null) }

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        DrinkType.entries.forEach {
            FilterChip(
                label = { Text(it.name) },
                selected = selectedDrinkType == it,
                onClick = {
                    if (selectedDrinkType == it) {
                        selectedDrinkType = null
                        onSelect(null)
                    } else {
                        selectedDrinkType = it
                        onSelect(it)
                    }
                }
            )
        }
    }
}