package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.Abv
import data.drink.Units
import data.drink.Volume
import ui.components.input.AbvInput
import ui.components.input.UnitsInput
import ui.components.input.VolumeInput
import utils.abvFromUnitsAndVolume
import utils.unitsFromAbvAndVolume


@Composable
fun UnitCalculator() {
    var units: Units by remember { mutableStateOf(0f) }
    var abv: Abv by remember { mutableStateOf(0f) }
    var volume: Volume by remember { mutableStateOf(0) }

    fun recalculateUnits() {
        units = unitsFromAbvAndVolume(abv, volume)
    }

    fun recalculateAbv() {
        abv = abvFromUnitsAndVolume(units, volume)
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column {
            AbvInput(abv = abv) {
                abv = it
                recalculateUnits()
            }
            VolumeInput(volume = volume) {
                volume = it
                recalculateUnits()
            }
            UnitsInput(units = units) {
                units = it
                recalculateAbv()
            }
        }
    }
}