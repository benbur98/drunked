package ui.components.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CalendarViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalendarState.initialState)
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    dates = currentState.yearMonth.getDates()
                )
            }
        }
    }

    fun toNextMonth(nextMonth: YearMonth) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    yearMonth = nextMonth,
                    dates = nextMonth.getDates()
                )
            }
        }
    }

    fun toPreviousMonth(prevMonth: YearMonth) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    yearMonth = prevMonth,
                    dates = prevMonth.getDates()
                )
            }
        }
    }

    companion object {
        data class CalendarState(
            val yearMonth: YearMonth,
            val dates: List<Date>
        ) {
            companion object {
                val initialState = CalendarState(
                    yearMonth = YearMonth.now(),
                    dates = emptyList()
                )
            }

            data class Date(
                val dayOfMonth: String,
                val isSelected: Boolean
            ) {
                val dayPadded: String
                    get() = dayOfMonth.padStart(2, '0')
                
                companion object {
                    val Empty = Date("", false)
                }
            }
        }

    }
}