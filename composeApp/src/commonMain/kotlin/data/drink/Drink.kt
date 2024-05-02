package data.drink

import utils.unitsFromAbvAndVolume


typealias Units = Float


typealias Abv = Float

val Abv.multiplier: Float
    get() = this / 100f

fun Abv.toPercentageString(): String = "${this}%"


fun String.toUnits(): Units = this.toFloatOrNull() ?: 0.0f

fun String.toAbv(): Abv = this.toFloatOrNull() ?: 0.0f

fun String.toVolume(): Volume = this.toIntOrNull() ?: 0


data class Drink(
    val id: Int,
    val timestamp: String,
    val name: String,
    val abv: Abv,
    val volume: Volume,
) {
    val units: Units
        get() = unitsFromAbvAndVolume(abv, volume)
}

