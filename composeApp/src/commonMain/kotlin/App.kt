import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.drink.Drink
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.components.NewDrinkForm
import ui.components.UnitCalculator
import ui.components.drink.DrinkSelect
import utils.currentTime


@Composable
@Preview
fun App() {
    println(currentTime)
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Logo()

            UnitCalculator()

            DrinkSelect(drinks = drinks, onDrinkSelected = { drink ->
                println(drink)
            })

            NewDrinkForm { drink ->
                println(drink)
            }
        }
    }
}


val drinks = listOf(
    Drink(
        timestamp = currentTime,
        name = "Beer",
        abv = 5f,
        volume = 330,
        units = 1f
    ),
    Drink(
        timestamp = currentTime,
        name = "Wine",
        abv = 12f,
        volume = 175,
        units = 1f
    ),
)
