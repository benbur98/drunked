import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import di.AppModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.SessionRecordingPage
import ui.components.Logo
import ui.components.NewDrinkForm
import ui.components.display.DrinkList
import ui.theme.DrunkedTheme


@Composable
@Preview
fun App(appModule: AppModule) {
    val database = appModule.database

    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionViewModel: SessionViewModel = viewModel { SessionViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    DrunkedTheme {
        Surface {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Logo()

                Button(onClick = { sessionViewModel.startSession() }, enabled = !sessionViewModel.sessionOngoing) {
                    Text("Start Session")
                }

                DrinkList(drinks)

                NewDrinkForm {
                    drinkViewModel.addDrink(it)
                }

                if (sessionViewModel.sessionOngoing) {
                    SessionRecordingPage(drinkViewModel, sessionViewModel)
                }
            }
        }
    }
}