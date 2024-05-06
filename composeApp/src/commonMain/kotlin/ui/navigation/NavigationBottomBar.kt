package ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp


sealed class NavigationBarItem(var title: String, var icon: ImageVector, var screen: Screen) {
    object Home : NavigationBarItem("Home", Icons.Default.Home, Screen.HomeScreen)
    object Drinks : NavigationBarItem("Drinks", Icons.Default.List, Screen.DrinksScreen)
    object Sessions : NavigationBarItem("Sessions", Icons.Default.DateRange, Screen.SessionsScreen)
}


@Composable
fun NavigationBottomBar(currentScreen: Screen, navigateTo: (Screen) -> Unit) {
    val navItems = listOf(NavigationBarItem.Sessions, NavigationBarItem.Home, NavigationBarItem.Drinks)

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = Color.Black
    ) {
        navItems.forEach { navItem ->
            BottomNavigationItem(
                icon = { Icon(navItem.icon, contentDescription = navItem.title) },
                label = { Text(text = navItem.title, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentScreen == navItem.screen,
                onClick = { navigateTo(navItem.screen) }
            )
        }
    }
}