package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.Drink
import data.drink.DrinkEvent
import data.drink.Units
import data.drink.Volume
import ui.components.input.DrinkSelect
import ui.components.input.UnitsInput
import ui.components.input.VolumeInput
import utils.currentTime
import utils.unitsFromAbvAndVolume
import utils.volumeFromAbvAndUnits


@Composable
fun NewDrinkEventForm(drinks: List<Drink>, onDrinkEventAdded: (DrinkEvent) -> Unit) {
    var drink: Drink? by remember { mutableStateOf(null) }
    var volume: Volume by remember { mutableStateOf(0) }
    var units: Units by remember { mutableStateOf(0f) }

    fun recalculateVolume() {
        drink?.run { volume = volumeFromAbvAndUnits(this.abv, units) }
    }

    fun recalculateUnits() {
        drink?.run { units = unitsFromAbvAndVolume(this.abv, volume) }
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                DrinkSelect(drinks) { drink = it }
                VolumeInput(volume) {
                    volume = it
                    recalculateUnits()
                }
                UnitsInput(units) {
                    units = it
                    recalculateVolume()
                }
            }
            Button(
                onClick = {
                    onDrinkEventAdded(
                        DrinkEvent(
                            timestamp = currentTime,
                            drink = drink!!,
                            volume = volume,
                            units = units
                        )
                    )
                }) {
                Text("Add DrinkEvent")
            }
        }
    }
}