package ui.components.drink

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import data.drink.DrinkType


@Composable
fun DrinkTypeSelect(onDrinkTypeSelected: (DrinkType) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedValue: DrinkType? by remember { mutableStateOf(null) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedValue?.text ?: "Select")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, scrollState = rememberScrollState()) {
            DrinkType.entries.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedValue = it
                    onDrinkTypeSelected(it)
                }) {
                    Text(it.text)
                }
            }
        }
    }
}
