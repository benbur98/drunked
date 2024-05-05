package ui.components.select

import androidx.compose.foundation.layout.fillMaxWidth
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
import data.drink.DrinkType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkTypeSelect(onDrinkTypeSelected: (DrinkType) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedValue: DrinkType? by remember { mutableStateOf(null) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedValue?.text ?: "Select Drink Type",
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DrinkType.entries.forEach {
                DropdownMenuItem(
                    text = { Text(it.text) },
                    onClick = {
                        expanded = false
                        selectedValue = it
                        onDrinkTypeSelected(it)
                    }
                )
            }
        }
    }
}
