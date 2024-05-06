import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import data.drink.datasources.SessionDataSource
import di.AppModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.navigation.NavigationBottomBar
import ui.navigation.NavigationGraph
import ui.navigation.Screen
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

    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.fromRoute(backStackEntry?.destination?.route)

    val navigateTo = { screen: Screen ->
        navController.navigate(screen.route)
    }

    DrunkedTheme {
        Scaffold(
            topBar = { Logo() },
            bottomBar = { NavigationBottomBar(currentScreen, navigateTo) }
        ) {
            Surface {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NavigationGraph(navController)

                    Button(onClick = { sessionViewModel.startSession() }, enabled = !sessionViewModel.sessionOngoing) {
                        Text("Start Session")
                    }

//                NewDrinkForm {
//                    drinkViewModel.addDrink(it)
//                }

//                DrinkList(drinks)

//                if (session == null) {
//                    SessionList(pastSessions)
//                    Spacer(modifier = Modifier.height(16.dp))
//                    SessionDetail(pastSessions.last(), DrinkEventDataSource(database))
//                } else {
//                    SessionRecordingPage(drinkViewModel, sessionViewModel)
//                }
                }
            }
        }
    }
}