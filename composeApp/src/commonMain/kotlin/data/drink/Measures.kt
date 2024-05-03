package data.drink

import kotlin.math.round


typealias Volume = Int  // Milliliters

val Volume.liters: Float
    get() = this / 1000f

fun Float.toVolume(): Volume = round(this * 1000).toInt()


enum class DrinkMeasure(val text: String, val volume: Volume) {
    SINGLE_SHOT("Single", 25),
    DOUBLE_SHOT("Double", 50),
    BOTTLE("Bottle", 330),
    PINT("Pint", 568)
}