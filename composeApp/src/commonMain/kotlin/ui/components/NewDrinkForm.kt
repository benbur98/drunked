package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.Abv
import data.drink.Drink
import data.drink.Units
import data.drink.Volume
import ui.components.drink.AbvInput
import ui.components.drink.UnitsInput
import ui.components.drink.VolumeInput


@Composable
fun NewDrinkForm(onDrinkAdded: (Drink) -> Unit) {
    var name: String by remember { mutableStateOf("") }
    var units: Units by remember { mutableStateOf(0f) }
    var abv: Abv by remember { mutableStateOf(0f) }
    var volume: Volume by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                AbvInput(abv = abv) { abv = it }
                VolumeInput(volume = volume) { volume = it }
                UnitsInput(units = units) { units = it }
            }
            Button(
                onClick = {
                    onDrinkAdded(
                        Drink(
                            name = name,
                            abv = abv,
                            volume = volume,
                            units = units
                        )
                    )
                }) {
                Text("Add Drink")
            }
        }
    }
}