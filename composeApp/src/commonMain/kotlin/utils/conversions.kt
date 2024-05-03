package utils

import data.drink.Abv
import data.drink.Units
import data.drink.Volume
import data.drink.liters
import data.drink.toVolume


fun unitsFromAbvAndVolume(abv: Abv, volume: Volume): Units {
    return abv * volume.liters
}

fun abvFromUnitsAndVolume(units: Float, volume: Volume): Abv {
    return (units / volume.liters) * 100
}

fun volumeFromAbvAndUnits(abv: Abv, units: Units): Volume {
    return (units / abv).toVolume()
}


class DrinkConverter {
    private var _percentage: Abv = 0f
    var percentage: Abv
        get() = _percentage
        set(value) {
            _percentage = value
            _units = unitsFromAbvAndVolume(value, volume)
            _volume = volumeFromAbvAndUnits(value, units)
        }

    private var _volume: Volume = 0
    var volume: Volume
        get() = _volume
        set(value) {
            _volume = value
            _units = unitsFromAbvAndVolume(percentage, value)
            _percentage = abvFromUnitsAndVolume(units, value)
        }

    private var _units: Units = 0f
    var units: Units
        get() = _units
        set(value) {
            _units = value
            _percentage = abvFromUnitsAndVolume(value, volume)
            _volume = volumeFromAbvAndUnits(percentage, value)
        }
}