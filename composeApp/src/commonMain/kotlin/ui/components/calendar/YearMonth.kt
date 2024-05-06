package ui.components.calendar

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import utils.getCurrentDateTime


class YearMonth(private val year: Int, private val month: Int) {

    fun incrementMonth(): YearMonth {
        return if (month == 12) {
            YearMonth(year + 1, 1)
        } else {
            YearMonth(year, month + 1)
        }
    }

    fun decrementMonth(): YearMonth {
        return if (month == 1) {
            YearMonth(year - 1, 12)
        } else {
            YearMonth(year, month - 1)
        }
    }

    fun getDates(): List<CalendarViewModel.Companion.CalendarState.Date> {
        val firstDayOfMonth = LocalDate(year, month, 1)
        val daysFromMonday = (firstDayOfMonth.dayOfWeek.ordinal - DayOfWeek.MONDAY.ordinal + 7) % 7
        val firstMondayBeforeFirstDayOfMonth = if (daysFromMonday == 0) firstDayOfMonth else firstDayOfMonth.minus(DatePeriod(days = daysFromMonday))
        val firstDayOfNextMonth = firstDayOfMonth.plus(DatePeriod(months = 1))

        return generateSequence(firstMondayBeforeFirstDayOfMonth) { it.plus(DatePeriod(days = 1)) }
            .takeWhile { it < firstDayOfNextMonth }
            .map {
                CalendarViewModel.Companion.CalendarState.Date(
                    dayOfMonth = if (it.monthNumber == month) {
                        "${it.dayOfMonth}"
                    } else {
                        ""
                    },
                    isSelected = it == getCurrentDateTime().date && it.monthNumber == month
                )
            }
            .toList()
    }

    val displayName: String
        get() {
            val monthString = Month.entries[month - 1].name.lowercase().capitalize()
            return "$monthString $year"
        }

    val timestamp: String
        get() = "$year-${month.toString().padStart(2, '0')}"

    companion object {
        fun now() = YearMonth(getCurrentDateTime().year, getCurrentDateTime().monthNumber)
    }
}