package data.drink.datasources

import app.cash.sqldelight.adapter.primitive.FloatColumnAdapter
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.DrinkEvent


class DrinkEventDataSource(private val database: DrunkedDatabase) {
    private val drinkEventQueries = database.drinkEventQueries

    private fun drinkEventMapper(timestamp: String, drink_id: Int, volume: Int, units: Float): DrinkEvent =
        DrinkEvent(timestamp = timestamp, drink = DrinkDataSource(database).getDrinkById(drink_id), volume = volume, units = units)

    fun getAllDrinkEvents(): List<DrinkEvent> {
        return drinkEventQueries.selectAll(::drinkEventMapper).executeAsList()
    }

    fun insertDrink(drinkEvent: DrinkEvent, drink_id: Int) {
        drinkEventQueries.insert(
            timestamp = drinkEvent.timestamp,
            drink_id = drink_id,
            volume = drinkEvent.volume,
            units = drinkEvent.units
        )
    }

    companion object {
        val drinkEventAdapter = com.drunked.drunked.database.DrinkEvent.Adapter(
            drink_idAdapter = IntColumnAdapter,
            volumeAdapter = IntColumnAdapter,
            unitsAdapter = FloatColumnAdapter
        )
    }
}
