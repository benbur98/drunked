package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import data.drink.DrinkMeasure
import data.drink.toAbv
import data.drink.toUnits
import data.drink.toVolume
import utils.abvFromUnitsAndVolume
import utils.unitsFromAbvAndVolume


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UnitCalculator() {
    var units: String by remember { mutableStateOf("") }
    var abv: String by remember { mutableStateOf("") }
    var volume: String by remember { mutableStateOf("") }

    fun recalculateUnits() {
        val abvFloat = abv.toAbv()
        val volumeInt = volume.toVolume()
        units = unitsFromAbvAndVolume(abvFloat, volumeInt).toString()
    }

    fun recalculateAbv() {
        val unitsFloat = units.toUnits()
        val volumeFloat = volume.toVolume()
        abv = abvFromUnitsAndVolume(unitsFloat, volumeFloat).toString()
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column {
            TextField(
                value = abv,
                onValueChange = { newValue ->
                    abv = newValue.toAbv().toString()
                    recalculateUnits()
                },
                label = { Text("Alcohol Percentage") },
                trailingIcon = { Text("%") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row {
                Column {
                    Row {
                        DrinkMeasure.entries.forEach {
                            Chip(
                                onClick = {
                                    volume = it.volume.toString()
                                    recalculateUnits()
                                },
                                content = { Text(it.text) }
                            )
                        }
                    }
                    TextField(
                        value = volume,
                        onValueChange = { newValue ->
                            volume = newValue.toVolume().toString()
                            recalculateUnits()
                        },
                        label = { Text("Volume") },
                        trailingIcon = { Text("ml") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
            TextField(
                value = units,
                onValueChange = { newValue ->
                    units = newValue.toUnits().toString()
                    recalculateAbv()
                },
                label = { Text("Alcohol Units") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}