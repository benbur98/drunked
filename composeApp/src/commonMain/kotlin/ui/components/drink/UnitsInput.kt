package ui.components.drink

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import data.drink.Units
import data.drink.toUnits


@Composable
fun UnitsInput(units: Units, onUnitsChange: (Units) -> Unit) {
    TextField(
        value = units.toString(),
        onValueChange = { newValue ->
            onUnitsChange(newValue.toUnits())
        },
        label = { Text("Alcohol Units") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}