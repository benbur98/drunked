import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.components.NewDrinkForm
import ui.theme.DrunkedTheme


@Composable
@Preview
fun App() {
//    val database: DrunkedDatabase
//    val drinksViewModel: DrinkViewModel = DrinkViewModel(database)
//    val sessionViewModel: SessionViewModel = SessionViewModel(database)

    DrunkedTheme {
        Surface {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Logo()

//                Button(onClick = { sessionViewModel.startSession() }, enabled = !sessionViewModel.sessionOngoing) {
//                    Text("Start Session")
//                }

                NewDrinkForm {
                    println(it)
                }

//                if (sessionViewModel.sessionOngoing) {
//                    SessionRecordingPage(drinksViewModel, sessionViewModel)
//                }
            }
        }
    }
}