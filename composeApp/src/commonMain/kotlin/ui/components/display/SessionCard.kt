package ui.components.display

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.drink.Session


@Composable
fun SessionCard(session: Session) {
    Card(modifier = Modifier.padding(4.dp).border(1.dp, color = Color.Black)) {
        Text("Session: ${session.date}")
    }
}