package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp


sealed class NavigationBarItem(var title: String, var selectedIcon: ImageVector, var unselectedIcon: ImageVector, var screen: Screen) {
    object Home : NavigationBarItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen)
    object Drinks : NavigationBarItem("Drinks", Icons.AutoMirrored.Filled.List, Icons.AutoMirrored.Outlined.List, Screen.DrinksScreen)
    object SessionHistory : NavigationBarItem("Sessions", Icons.Filled.DateRange, Icons.Outlined.DateRange, Screen.SessionHistoryScreen)
}


@Composable
fun NavigationBottomBar(currentScreen: Screen, navigateTo: (Screen) -> Unit) {
    val navItems = listOf(NavigationBarItem.SessionHistory, NavigationBarItem.Home, NavigationBarItem.Drinks)

    NavigationBar {
        navItems.forEach { navItem ->
            val selected = currentScreen == navItem.screen
            NavigationBarItem(
                icon = {
                    if (selected) Icon(navItem.selectedIcon, contentDescription = navItem.title)
                    else Icon(navItem.unselectedIcon, contentDescription = navItem.title)
                },
                label = { Text(text = navItem.title, fontSize = 9.sp) },
                alwaysShowLabel = true,
                selected = selected,
                onClick = { navigateTo(navItem.screen) }
            )
        }
    }
}