package ui.components.input

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.drink.Drink


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkSelect(drinks: List<Drink>, onDrinkSelected: (Drink) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedValue: Drink? by remember { mutableStateOf(null) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = selectedValue?.name ?: "Select Drink",
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            drinks.forEach {
                DropdownMenuItem(
                    text = { Text(it.name) },
                    onClick = {
                        expanded = false
                        selectedValue = it
                        onDrinkSelected(it)
                    }
                )
            }
        }
    }
}
