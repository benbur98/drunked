package ui.components.select

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.drink.DrinkType


@Composable
fun DrinkTypeSelectChips(onSelect: (List<DrinkType>?) -> Unit) {
    var selectedDrinkTypes: Set<DrinkType> by remember { mutableStateOf(setOf()) }

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        DrinkType.entries.forEach {
            FilterChip(
                label = { Text(it.name) },
                selected = it in selectedDrinkTypes,
                onClick = {
                    selectedDrinkTypes = if (it in selectedDrinkTypes) selectedDrinkTypes - it
                    else selectedDrinkTypes + it

                    onSelect(selectedDrinkTypes.toList().ifEmpty { null })
                },
                trailingIcon = {
                    if (it in selectedDrinkTypes) Icon(Icons.Default.Close, "Close")
                }
            )
        }
    }
}