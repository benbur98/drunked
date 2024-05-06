package ui.navigation.screens

import DrinkViewModel
import SessionViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drunked.drunked.database.DrunkedDatabase
import org.koin.compose.koinInject
import ui.SessionRecordingPage


@Composable
fun HomeScreen(database: DrunkedDatabase = koinInject()) {
    val drinkViewModel: DrinkViewModel = viewModel { DrinkViewModel(database) }
    val sessionViewModel: SessionViewModel = viewModel { SessionViewModel(database) }

    val session by sessionViewModel.session.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { sessionViewModel.startSession() }, enabled = session == null) {
            Text("Start Session")
        }

        if (session != null) {
            SessionRecordingPage(drinkViewModel, sessionViewModel)
        }
    }
}