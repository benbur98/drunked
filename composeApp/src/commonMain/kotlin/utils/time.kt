package utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


/* Format the Timestamp to YYY-MM-DD HH:MM:SS */
private fun LocalDateTime.format() = toString().replace("T", " ").split(".").first()


/* Return the Current DateTime in the System Default TimeZone */
private fun getCurrentDateTime(): LocalDateTime {
    val now = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(timeZone)
}


val currentTime = getCurrentDateTime().format()