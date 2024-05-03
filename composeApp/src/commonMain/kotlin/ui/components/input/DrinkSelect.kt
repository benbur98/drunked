package ui.components.input

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
import data.drink.Drink


@Composable
fun DrinkSelect(drinks: List<Drink>, onDrinkSelected: (Drink) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedValue: Drink? by remember { mutableStateOf(null) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedValue?.name ?: "Select")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, scrollState = rememberScrollState()) {
            drinks.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedValue = it
                    onDrinkSelected(it)
                }) {
                    Text(it.name)
                }
            }
        }
    }
}
