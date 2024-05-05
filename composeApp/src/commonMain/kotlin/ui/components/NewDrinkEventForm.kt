package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import data.drink.Drink
import data.drink.DrinkEvent
import data.drink.Session
import data.drink.Units
import data.drink.Volume
import ui.components.input.UnitsInput
import ui.components.input.VolumeInput
import ui.components.select.DrinkSelect
import utils.currentTime
import utils.unitsFromAbvAndVolume
import utils.volumeFromAbvAndUnits


@Composable
fun NewDrinkEventForm(drinks: List<Drink>, session: Session, onDrinkEventAdded: (DrinkEvent) -> Unit) {
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
                    if (drink == null || volume == 0 || units == 0f) return@Button
                    onDrinkEventAdded(
                        DrinkEvent(
                            timestamp = currentTime,
                            drink = drink!!,
                            volume = volume,
                            units = units,
                            session = session
                        )
                    )
                    drink = null
                    volume = 0
                    units = 0f
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                Text("Add DrinkEvent")
            }
        }
    }
}