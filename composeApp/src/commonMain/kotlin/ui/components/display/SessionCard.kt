package ui.components.display

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import data.drink.Session


@Composable
fun SessionCard(session: Session) {
    Text("Session: ${session.date}")
}