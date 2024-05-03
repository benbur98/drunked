package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import data.drink.Abv
import data.drink.Drink
import data.drink.DrinkType
import ui.components.input.AbvInput
import ui.components.input.DrinkTypeSelect


@Composable
fun NewDrinkForm(onDrinkAdded: (Drink) -> Unit) {
    var name: String by remember { mutableStateOf("") }
    var abv: Abv by remember { mutableStateOf(0f) }
    var drinkType: DrinkType? by remember { mutableStateOf(null) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                AbvInput(abv = abv) { abv = it }
                DrinkTypeSelect { drinkType = it }
            }
            Button(
                onClick = {
                    if (name.isBlank() || abv == 0f) return@Button
                    onDrinkAdded(
                        Drink(
                            name = name,
                            abv = abv,
                            type = drinkType ?: DrinkType.UNKNOWN
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                Text("Add Drink")
            }
        }
    }
}