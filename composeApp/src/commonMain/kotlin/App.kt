import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.Logo
import ui.navigation.NavigationBottomBar
import ui.navigation.NavigationGraph
import ui.navigation.Screen
import ui.theme.DrunkedTheme


@Composable
@Preview
fun App() {
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
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding).padding(top = 10.dp)) {
                NavigationGraph(navController)
            }
        }
    }
}