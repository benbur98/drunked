package ui.navigation.screens

import DrinkViewModel
import SessionViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.SessionRecordingPage


@Composable
fun HomeScreen(database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionViewModel: SessionViewModel = viewModel { SessionViewModel(database) }

    Column {
        Text("HOME")

        Button(onClick = { sessionViewModel.startSession() }, enabled = !sessionViewModel.sessionOngoing) {
            Text("Start Session")
        }

        if (sessionViewModel.sessionOngoing) {
            SessionRecordingPage(drinkViewModel, sessionViewModel)

            Button(onClick = { sessionViewModel.endSession() }) {
                Text("End Session")
            }
        }
    }
}