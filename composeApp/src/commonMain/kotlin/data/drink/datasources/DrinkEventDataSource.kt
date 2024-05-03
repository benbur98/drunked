package data.drink.datasources

import app.cash.sqldelight.adapter.primitive.FloatColumnAdapter
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.DrinkEvent


class DrinkEventDataSource(private val database: DrunkedDatabase) {
    private val drinkEventQueries = database.drinkEventQueries

    private fun drinkEventMapper(id: Int, timestamp: String, volume: Int, units: Float, drink_id: Int, session_id: Int): DrinkEvent =
        DrinkEvent(
            volume = volume,
            units = units,
            drink = DrinkDataSource(database).getDrinkById(drink_id),
            session = SessionDataSource(database).getSessionById(session_id),
            timestamp = timestamp,
            id = id
        )

    fun getDrinkEventById(id: Int): DrinkEvent {
        return drinkEventQueries.selectById(id, ::drinkEventMapper).executeAsOne()
    }

    fun getAllDrinkEvents(): List<DrinkEvent> {
        return drinkEventQueries.selectAll(::drinkEventMapper).executeAsList()
    }

    fun getDrinkEventsForSession(sessionId: Int): List<DrinkEvent> {
        return drinkEventQueries.selectAllForSession(sessionId, ::drinkEventMapper).executeAsList()
    }

    fun insertDrinkEvent(drinkEvent: DrinkEvent) {
        drinkEventQueries.insert(
            timestamp = drinkEvent.timestamp,
            volume = drinkEvent.volume,
            units = drinkEvent.units,
            drink_id = drinkEvent.drink.id!!,
            session_id = drinkEvent.session.id!!
        )
    }

    companion object {
        val drinkEventAdapter = com.drunked.drunked.database.DrinkEvent.Adapter(
            idAdapter = IntColumnAdapter,
            volumeAdapter = IntColumnAdapter,
            unitsAdapter = FloatColumnAdapter,
            drink_idAdapter = IntColumnAdapter,
            session_idAdapter = IntColumnAdapter,
        )
    }
}
