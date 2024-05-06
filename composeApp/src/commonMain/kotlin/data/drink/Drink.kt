package data.drink

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import utils.currentDate
import utils.currentTime


enum class DrinkType(val text: String) {
    BEER("Beer"),
    CIDER("Cider"),
    WINE("Wine"),
    SPIRIT("Spirit"),
    COCKTAIL("Cocktail"),
    OTHER("Other"),
    UNKNOWN("Unknown");
}


data class Drink(
    val name: String,
    val abv: Abv,
    val type: DrinkType = DrinkType.UNKNOWN,
    val id: Int? = null
)


data class DrinkEvent(
    val drink: Drink,
    val volume: Volume,
    val units: Units,
    val session: Session,
    val timestamp: String = currentTime,
    val id: Int? = null
)


data class Session(
    val date: String = currentDate,
    val id: Int? = null,
) {
    val datetime: LocalDateTime
        get() = LocalDateTime.parse(date, DateTimePeriod("yyyy-MM-dd"))
}
