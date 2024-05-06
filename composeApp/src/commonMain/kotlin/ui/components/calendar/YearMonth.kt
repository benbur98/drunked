package ui.components.calendar

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
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

    private fun getDayOfMonthStartingFromMonday(): List<LocalDate> {
        val firstDayOfMonth = LocalDate(year, month, 1)
        val daysUntilMonday = (DayOfWeek.MONDAY.ordinal - firstDayOfMonth.dayOfWeek.ordinal + 7) % 7
        val firstMondayOfMonth = firstDayOfMonth.plus(DatePeriod(days = daysUntilMonday))
        val firstDayOfNextMonth = firstDayOfMonth.plus(DatePeriod(months = 1))

        return generateSequence(firstMondayOfMonth) { it.plus(DatePeriod(days = 1)) }
            .takeWhile { it < firstDayOfNextMonth }
            .toList()
    }

    fun getDates(): List<CalendarViewModel.Companion.CalendarState.Date> {
        return this.getDayOfMonthStartingFromMonday()
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