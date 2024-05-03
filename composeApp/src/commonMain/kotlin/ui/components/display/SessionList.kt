package ui.components.display

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import data.drink.Session


@Composable
fun SessionList(sessions: List<Session>) {
    LazyColumn {
        items(sessions) { session ->
            SessionCard(session)
        }
    }
}