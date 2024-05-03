import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import di.AppModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.SessionRecordingPage
import ui.components.Logo
import ui.components.display.SessionDetail
import ui.components.display.SessionList
import ui.theme.DrunkedTheme


@Composable
@Preview
fun App(appModule: AppModule) {
    val database = appModule.database

    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionViewModel: SessionViewModel = viewModel { SessionViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()
    val session by sessionViewModel.session.collectAsState()

    val pastSessions = SessionDataSource(database).getAllSessions()

    DrunkedTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()

                Button(onClick = { sessionViewModel.startSession() }, enabled = !sessionViewModel.sessionOngoing) {
                    Text("Start Session")
                }

//                DrinkList(drinks)

//                NewDrinkForm {
//                    drinkViewModel.addDrink(it)
//                }s

                if (session == null) {
                    SessionList(pastSessions)
                    Spacer(modifier = Modifier.height(16.dp))
                    SessionDetail(pastSessions.last(), DrinkEventDataSource(database))
                } else {
                    SessionRecordingPage(drinkViewModel, sessionViewModel)
                }
            }
        }
    }
}