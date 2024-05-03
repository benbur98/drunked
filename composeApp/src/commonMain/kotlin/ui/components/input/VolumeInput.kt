package ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import data.drink.DrinkMeasure
import data.drink.Volume
import data.drink.toVolume


@Composable
fun VolumeInput(volume: Volume, onVolumeChanged: (Volume) -> Unit) {
    Column {
        Row {
            DrinkMeasure.entries.forEach {
                SuggestionChip(
                    onClick = {
                        onVolumeChanged(it.volume)
                    },
                    label = { Text(it.text) }
                )
            }
        }
        TextField(
            value = volume.toString(),
            onValueChange = { newValue ->
                onVolumeChanged(newValue.toVolume())
            },
            label = { Text("Volume") },
            trailingIcon = { Text("ml") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
