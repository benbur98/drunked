import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Drink
import data.drink.datasources.DrinkDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DrinkViewModel(database: DrunkedDatabase) {
    private val drinkDataSource = DrinkDataSource(database)

    private val _drinks = MutableStateFlow<List<Drink>>(emptyList())
    val drinks = _drinks.asStateFlow()

    fun addDrink(drink: Drink) {
        drinkDataSource.insertDrink(drink)
        _drinks.update {
            it + drink
        }
    }
}