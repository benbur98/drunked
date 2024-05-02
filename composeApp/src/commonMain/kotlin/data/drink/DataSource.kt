package data.drink

import com.drunked.drunked.database.DrunkedDatabase


class DrinkDataSource(database: DrunkedDatabase) {
    private val drinkQueries = database.drinkQueries

    private fun drinkMapper(drink: String, abv: Float, volume: Int, units: Float, type: DrinkType?): Drink =
        Drink(name = drink, abv = abv, volume = volume, units = units, type = type)

    fun getAllDrinks(): List<Drink> {
        return drinkQueries.selectAll(::drinkMapper).executeAsList()
    }

    fun insertDrink(drink: Drink) {
        drinkQueries.insert(name = drink.name, abv = drink.abv, volume = drink.volume, units = drink.units, type = drink.type)
    }
}
