package data.drink

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.adapter.primitive.FloatColumnAdapter
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.drunked.drunked.database.DrunkedDatabase


class DrinkDataSource(database: DrunkedDatabase) {
    private val drinkQueries = database.drinkQueries

    private fun drinkMapper(drink: String, abv: Float, volume: Int, units: Float, type: DrinkType): Drink =
        Drink(name = drink, abv = abv, volume = volume, units = units, type = type)

    fun getAllDrinks(): List<Drink> {
        return drinkQueries.selectAll(::drinkMapper).executeAsList()
    }

    fun insertDrink(drink: Drink) {
        drinkQueries.insert(name = drink.name, abv = drink.abv, volume = drink.volume, units = drink.units, type = drink.type)
    }

    companion object {
        val drinkAdapter = com.drunked.drunked.database.Drink.Adapter(
            abvAdapter = FloatColumnAdapter,
            volumeAdapter = IntColumnAdapter,
            unitsAdapter = FloatColumnAdapter,
            typeAdapter = EnumColumnAdapter()
        )

//        val drinkTypeAdapter = object : ColumnAdapter<DrinkType?, String?> {
//            override fun decode(databaseValue: String?): DrinkType? {
//                return databaseValue?.let { DrinkType.valueOf(it) }
//            }
//
//            override fun encode(value: DrinkType?): String? {
//                return value?.name
//            }
//        }
    }
}
