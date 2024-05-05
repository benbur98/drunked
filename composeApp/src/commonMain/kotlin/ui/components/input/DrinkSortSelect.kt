package ui.components.input

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


enum class SortType(val text: String) {
    NAME_ASC("Name Ascending"),
    NAME_DESC("Name Descending"),
    ABV_ASC("ABV Ascending"),
    ABV_DESC("ABV Descending")
}


@Composable
fun DrinkSortSelect(onSelect: (SortType) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var sortType: SortType by remember { mutableStateOf(SortType.NAME_ASC) }
    var filterApplied by remember { mutableStateOf(false) }

    InputChip(
        label = { Text(sortType.text) },
        selected = filterApplied,
        onClick = { expanded = !expanded },
        trailingIcon = {
            when (sortType) {
                SortType.NAME_ASC, SortType.ABV_ASC -> {
                    Icon(Icons.Default.KeyboardArrowUp, "Ascending")
                }

                SortType.NAME_DESC, SortType.ABV_DESC -> {
                    Icon(Icons.Default.KeyboardArrowDown, "Descending")
                }
            }
        },
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        SortType.entries.forEach {
            DropdownMenuItem(
                text = { Text(it.text) },
                onClick = {
                    expanded = false
                    sortType = it
                    onSelect(it)
                }
            )
        }
    }
}