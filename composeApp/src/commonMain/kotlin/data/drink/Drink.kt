package data.drink


typealias Units = Float


typealias Abv = Float

val Abv.multiplier: Float
    get() = this / 100f

fun Abv.toPercentageString(): String = "${this}%"


fun String.toUnits(): Units = this.toFloatOrNull() ?: 0.0f

fun String.toAbv(): Abv = this.toFloatOrNull()?.coerceIn(0f, 100f) ?: 0.0f

fun String.toVolume(): Volume = this.toIntOrNull() ?: 0


data class Drink(
    val name: String,
    val abv: Abv,
    val volume: Volume,
    val units: Units,
    val type: DrinkType = DrinkType.UNKNOWN
)


enum class DrinkType {
    BEER,
    CIDER,
    WINE,
    SPIRIT,
    COCKTAIL,
    UNKNOWN
}