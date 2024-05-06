package ui.navigation.screens

import DrinkViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.components.DrinkFloatingButton
import ui.components.NewDrinkForm
import ui.components.UnitCalculator
import ui.components.display.DrinkList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    var showUnitCalculator by remember { mutableStateOf(false) }
    var showAddDrinkForm by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = { DrinkFloatingButton { showAddDrinkForm = true } },
    ) {
        DrinkList(drinks)

        if (showAddDrinkForm) {
            ModalBottomSheet(
                onDismissRequest = { showAddDrinkForm = false },
                sheetState = rememberModalBottomSheetState(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "New Drink",
                        modifier = Modifier.weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    AssistChip(
                        onClick = { showUnitCalculator = true },
                        label = { Text("Unit Calculator") },
                        modifier = Modifier.wrapContentWidth(Alignment.End)
                    )
                }
                if (showUnitCalculator) {
                    Dialog(onDismissRequest = { showUnitCalculator = false }) {
                        UnitCalculator()
                    }
                }
                NewDrinkForm {
                    drinkViewModel.addDrink(it)
                    showAddDrinkForm = false
                }
            }
        }
    }
}