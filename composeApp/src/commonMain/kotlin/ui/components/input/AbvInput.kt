package ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import data.drink.Abv
import data.drink.toAbv


@Composable
fun AbvInput(abv: Abv, onAbvChange: (Abv) -> Unit) {
    TextField(
        value = abv.toString(),
        onValueChange = { newValue ->
            onAbvChange(newValue.toAbv())
        },
        label = { Text("Alcohol Percentage") },
        trailingIcon = { Text("%") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}