import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.drunked.drunked.database.DrunkedDatabase
import di.AppModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.navigation.NavigationBottomBar
import ui.navigation.Screen
import ui.theme.DrunkedTheme


class DatabaseViewModel(val database: DrunkedDatabase) : ViewModel()


@Composable
@Preview
fun App(appModule: AppModule) {
    val database = appModule.database
    val databaseViewModel: DatabaseViewModel = viewModel { DatabaseViewModel(database) }

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
//                    NavigationGraph(navController, databaseViewModel)
                }
            }
        }
    }
}