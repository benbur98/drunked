package data.drink


enum class DrinkType(val text: String) {
    BEER("Beer"),
    CIDER("Cider"),
    WINE("Wine"),
    SPIRIT("Spirit"),
    COCKTAIL("Cocktail"),
    UNKNOWN("Unknown");
}


data class Drink(
    val name: String,
    val abv: Abv,
    val type: DrinkType = DrinkType.UNKNOWN,
    val id: Int? = null
)


data class DrinkEvent(
    val timestamp: String,
    val drink: Drink,
    val volume: Volume,
    val units: Units,
)
