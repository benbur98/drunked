package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.toAbv
import data.drink.toUnits
import data.drink.toVolume
import ui.components.drink.AbvInput
import ui.components.drink.UnitsInput
import ui.components.drink.VolumeInput
import utils.abvFromUnitsAndVolume
import utils.unitsFromAbvAndVolume


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
            AbvInput(abv = abv.toAbv()) {
                abv = it.toString()
                recalculateUnits()
            }
            VolumeInput(volume = volume.toVolume()) {
                volume = it.toString()
                recalculateUnits()
            }
            UnitsInput(units = units.toUnits()) {
                units = it.toString()
                recalculateAbv()
            }
        }
    }
}