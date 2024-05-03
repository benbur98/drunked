import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.drink.Drink
import data.drink.DrinkType
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.components.NewDrinkEventForm
import ui.components.NewDrinkForm
import utils.currentTime


@Composable
@Preview
fun App() {
    println(currentTime)
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Logo()

            NewDrinkForm {
                println(it)
            }

            NewDrinkEventForm(drinks) {
                println(it)
            }
        }
    }
}


val drinks = listOf(
    Drink(
        name = "Beer1",
        abv = 5.0f,
        type = DrinkType.BEER
    ),
    Drink(
        name = "Wine1",
        abv = 12.0f,
        type = DrinkType.WINE
    )
)