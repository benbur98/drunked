package ui.navigation.screens

import DrinkViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import ui.components.NewDrinkForm
import ui.components.display.DrinkList


@Composable
fun DrinksScreen(database: DrunkedDatabase) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    Column {
        Text("DRINKS")

        NewDrinkForm {
            drinkViewModel.addDrink(it)
        }

        DrinkList(drinks)
    }
}