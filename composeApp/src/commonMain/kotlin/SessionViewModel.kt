import androidx.lifecycle.ViewModel
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.DrinkEvent
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SessionViewModel(database: DrunkedDatabase) : ViewModel() {
    private val drinkEventDataSource = DrinkEventDataSource(database)
    private val sessionDataSource = SessionDataSource(database)

    private val _session = MutableStateFlow<Session?>(null)
    val session = _session.asStateFlow()

    val sessionOngoing: Boolean
        get() = _session.value != null

    fun startSession() {
        _session.update { sessionDataSource.insertAndReturnSession(Session()) }
    }

    fun resumeSession(id: Int) {
        _session.update { sessionDataSource.getSessionById(id) }
    }

    fun endSession() {
        _session.update { null }
    }

    private val _drinkEvents = MutableStateFlow<List<DrinkEvent>>(emptyList())
    val drinkEvents = _drinkEvents.asStateFlow()

    fun addDrinkEvent(drinkEvent: DrinkEvent) {
        drinkEventDataSource.insertDrinkEvent(drinkEvent)
        _drinkEvents.update { it + drinkEvent }
    }

    fun getSessionDrinkEvents() {
        if (!sessionOngoing) return
        drinkEventDataSource.getDrinkEventsForSession(session.value!!.id!!)
    }
}