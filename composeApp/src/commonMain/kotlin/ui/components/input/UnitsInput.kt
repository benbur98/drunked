package ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}