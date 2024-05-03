import com.drunked.drunked.database.DrunkedDatabase
import data.drink.DrinkEvent
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SessionViewModel(database: DrunkedDatabase) {
    private val drinkEventDataSource = DrinkEventDataSource(database)
    private val sessionDataSource = SessionDataSource(database)

    lateinit var session: Session

    val sessionOngoing: Boolean
        get() = ::session.isInitialized

    fun startSession() {
        session = sessionDataSource.insertAndReturnSession(Session())
    }

    fun resumeSession(id: Int) {
        session = sessionDataSource.getSessionById(id)
    }

    private val _drinkEvents = MutableStateFlow<List<DrinkEvent>>(emptyList())
    val drinkEvents = _drinkEvents.asStateFlow()

    fun addDrinkEvent(drinkEvent: DrinkEvent) {
        drinkEventDataSource.insertDrinkEvent(drinkEvent)
        _drinkEvents.update {
            it + drinkEvent
        }
    }
    
    fun getSessionDrinkEvents() {
        drinkEventDataSource.getDrinkEventsForSession(session.id!!)
    }
}