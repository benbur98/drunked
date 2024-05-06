package ui.navigation.screens

import DrinkViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.components.DrinkFloatingButton
import ui.components.NewDrinkForm
import ui.components.display.DrinkList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = { DrinkFloatingButton { showBottomSheet = true } },
    ) {
        DrinkList(drinks)

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
            ) {
                NewDrinkForm {
                    drinkViewModel.addDrink(it)
                    showBottomSheet = false
                }
            }
        }
    }
}