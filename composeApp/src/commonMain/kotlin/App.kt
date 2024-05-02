import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.components.NewDrinkForm
import ui.components.UnitCalculator
import utils.currentTime


@Composable
@Preview
fun App() {
    println(currentTime)
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Logo()

            UnitCalculator()

            NewDrinkForm { drink ->
                println(drink)
            }
        }
    }
}
