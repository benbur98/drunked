package ui.components.input

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


enum class SortType(val text: String) {
    NAME_ASC("Name Ascending"),
    NAME_DESC("Name Descending"),
    ABV_ASC("ABV Ascending"),
    ABV_DESC("ABV Descending")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkSortSelect(onSelect: (SortType) -> Unit) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var sortType: SortType by remember { mutableStateOf(SortType.NAME_ASC) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = sortType.text,
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
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
}