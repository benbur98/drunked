package data.drink.datasources

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.adapter.primitive.FloatColumnAdapter
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Drink
import data.drink.DrinkType


class DrinkDataSource(database: DrunkedDatabase) {
    private val drinkQueries = database.drinkQueries

    private fun drinkMapper(id: Int, drink: String, abv: Float, type: DrinkType): Drink =
        Drink(name = drink, abv = abv, type = type, id = id)

    fun getDrinkById(id: Int): Drink {
        return drinkQueries.selectById(id, ::drinkMapper).executeAsOne()
    }

    fun getAllDrinks(): List<Drink> {
        return drinkQueries.selectAll(::drinkMapper).executeAsList()
    }

    fun insertDrink(drink: Drink) {
        drinkQueries.insert(name = drink.name, abv = drink.abv, type = drink.type)
    }

    companion object {
        val drinkAdapter = com.drunked.drunked.database.Drink.Adapter(
            idAdapter = IntColumnAdapter,
            abvAdapter = FloatColumnAdapter,
            typeAdapter = EnumColumnAdapter<DrinkType>()
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
