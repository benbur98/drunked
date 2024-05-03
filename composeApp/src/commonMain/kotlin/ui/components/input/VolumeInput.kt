package ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import data.drink.DrinkMeasure
import data.drink.Volume
import data.drink.toVolume


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VolumeInput(volume: Volume, onVolumeChanged: (Volume) -> Unit) {
    Column {
        Row {
            DrinkMeasure.entries.forEach {
                Chip(
                    onClick = {
                        onVolumeChanged(it.volume)
                    },
                    content = { Text(it.text) }
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
