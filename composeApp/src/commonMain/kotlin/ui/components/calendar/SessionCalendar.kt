package ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import data.drink.Session
import kotlinx.datetime.DayOfWeek


@Composable
fun SessionCalendar(
    sessions: List<Session>,
    onSessionClicked: (Session) -> Unit = {},
) {
    val viewModel: CalendarViewModel = viewModel()
    val state = viewModel.state.collectAsState()

    val yearMonth = state.value.yearMonth
    val dates = state.value.dates
    val daysOfWeek = DayOfWeek.entries.map { it.name.substring(0, 3) }.toTypedArray()

    val sessionDates = sessions
        .filter {
            val sessionYearMonth = it.date.split("-")[0] + "-" + it.date.split("-")[1]
            sessionYearMonth == yearMonth.dateString
        }
        .map { it.date.split("-")[2] }

    val toYearMonth: (YearMonth) -> Unit = {
        viewModel.toYearMonth(it)
    }
    val onDateClicked = { date: CalendarViewModel.Companion.CalendarState.Date ->
        val timestamp = viewModel.state.value.yearMonth.dateString + "-" + date.dayPadded
        val clickedSession = sessions.find { session -> session.date == timestamp }
        if (clickedSession != null) onSessionClicked(clickedSession)
    }

//    var offsetX by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
//            .pointerInput(Unit) {
//                detectDragGestures { change, dragAmount ->
//                    val original = offsetX
//                    offsetX += dragAmount.x
//                    change.consume()
//                    if (original < offsetX) {
//                        // Swiped to the right
//                        toYearMonth(yearMonth.decrementMonth())
//                    } else if (original > offsetX) {
//                        // Swiped to the left
//                        toYearMonth(yearMonth.incrementMonth())
//                    }
//                }
//            }
    ) {
        Row {
            repeat(daysOfWeek.size) {
                val item = daysOfWeek[it]
                DayOfWeekItem(item, modifier = Modifier.weight(1f))
            }
        }
        Header(
            yearMonth = yearMonth,
            onMonthButtonClicked = toYearMonth
        )
        Content(
            dates = dates,
            markedDates = sessionDates,
            onDateClick = onDateClicked
        )
    }
}


@Composable
fun DayOfWeekItem(day: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = day,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.Center).padding(5.dp)
        )
    }
}


@Composable
fun Header(
    yearMonth: YearMonth,
    onMonthButtonClicked: (YearMonth) -> Unit,
) {
    Row {
        IconButton(onClick = { onMonthButtonClicked(yearMonth.decrementMonth()) }) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Left Arrow")
        }
        Text(
            text = yearMonth.displayName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )
        IconButton(onClick = { onMonthButtonClicked(yearMonth.incrementMonth()) }) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Right Arrow")
        }
    }
}


@Composable
fun Content(
    dates: List<CalendarViewModel.Companion.CalendarState.Date>,
    markedDates: List<String>,
    onDateClick: (CalendarViewModel.Companion.CalendarState.Date) -> Unit,
) {
    Column {
        var index = 0
        repeat(6) {
            if (index >= dates.size) return@repeat
            Row {
                repeat(7) {
                    val date =
                        if (index < dates.size) dates[index] else CalendarViewModel.Companion.CalendarState.Date.Empty
                    ContentItem(
                        date = date,
                        marked = date.dayPadded in markedDates,
                        onClick = onDateClick,
                        modifier = Modifier.weight(1f)
                    )
                    index++
                }
            }
        }
    }
}


@Composable
fun ContentItem(
    date: CalendarViewModel.Companion.CalendarState.Date,
    marked: Boolean,
    onClick: (CalendarViewModel.Companion.CalendarState.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (date.isSelected) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else if (marked) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    Color.Transparent
                },
                shape = MaterialTheme.shapes.extraLarge
            )
            .clickable { onClick(date) }
    ) {
        Text(
            text = date.dayOfMonth,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Center).padding(10.dp)
        )
    }
}