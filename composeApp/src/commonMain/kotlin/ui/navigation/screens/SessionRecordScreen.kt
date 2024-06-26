package ui.navigation.screens

import DrinkViewModel
import SessionRecordViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.components.NewDrinkEventForm
import ui.components.display.DrinkEventList
import ui.components.generic.OverviewCard
import kotlin.math.roundToInt


@Composable
fun SessionRecordScreen(toHomeScreen: () -> Unit, database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionRecordViewModel: SessionRecordViewModel =
        viewModel { SessionRecordViewModel(database) }

    val drinks by drinkViewModel.drinks.collectAsState()

    val drinkEvents by sessionRecordViewModel.drinkEvents.collectAsState()

    val session by sessionRecordViewModel.session.collectAsState()

    Column {
        IconButton(onClick = { toHomeScreen() }) {
            Icon(Icons.Default.Close, contentDescription = "End Session")
        }

        NewDrinkEventForm(drinks, session!!) {
            sessionRecordViewModel.addDrinkEvent(it)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OverviewCard(
            listOf(
                "Drinks" to drinkEvents.size.toString(),
                "Units" to drinkEvents.sumOf { it.units.toDouble() }.roundToInt().toString()
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        DrinkEventList(drinkEvents) {
            sessionRecordViewModel.deleteDrinkEvent(it)
        }
    }
}