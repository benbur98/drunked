package ui.components.display

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.drink.Session
import kotlinx.coroutines.launch


@Composable
private fun MonthHeader(month: String) {
    Text(month, modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.surface))
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SessionList(sessions: List<Session>, onClick: (Session) -> Unit) {
    val orderedSessions: List<Session> by remember { mutableStateOf(sessions.sortedBy { it.date }) }

    // Map the Sessions to be Grouped by the Month and it's Index
    val sessionMonthMap by remember {
        mutableStateOf(orderedSessions.mapIndexed { index, session ->
            session.datetime.month.name to index
        }.groupBy { it.first })
    }
    val sessionMonths by remember { mutableStateOf(sessionMonthMap.keys) }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).align(Alignment.Top)
        ) {
            sessionMonths.forEach {
                stickyHeader { MonthHeader(it) }
                items(sessionMonthMap[it]!!) { (month, index) ->
                    val session = orderedSessions[index]
                    Box(modifier = Modifier.clickable { onClick(session) }) {
                        SessionCard(session)
                    }
                }
            }
        }

        Column(modifier = Modifier.wrapContentWidth()) {
            sessionMonths.forEach {
                Text(
                    text = it,
                    modifier = Modifier.clickable {
                        sessionMonthMap[it]?.first()?.let { (month, index) ->
                            scope.launch {
                                listState.animateScrollToItem(index)
                            }
                        }
                    }.align(Alignment.End)
                )
            }
        }
    }
}