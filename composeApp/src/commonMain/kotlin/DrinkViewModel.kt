import androidx.lifecycle.ViewModel
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Drink
import data.drink.datasources.DrinkDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DrinkViewModel(database: DrunkedDatabase) : ViewModel() {
    private val drinkDataSource = DrinkDataSource(database)

    private val _drinks = MutableStateFlow<List<Drink>>(emptyList())
    val drinks = _drinks.asStateFlow()

    init {
        updateDrinks()
    }

    fun updateDrinks() {
        _drinks.update {
            drinkDataSource.getAllDrinks()
        }
    }

    fun addDrink(drink: Drink) {
        drinkDataSource.insertDrink(drink)
        _drinks.update {
            it + drink
        }
    }
}