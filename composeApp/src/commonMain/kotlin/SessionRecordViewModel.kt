import androidx.lifecycle.ViewModel
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.DrinkEvent
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SessionRecordViewModel(database: DrunkedDatabase, sessionId: Int? = null) : ViewModel() {
    private val drinkEventDataSource = DrinkEventDataSource(database)
    private val sessionDataSource = SessionDataSource(database)

    private val _session = MutableStateFlow<Session?>(null)
    val session = _session.asStateFlow()

    private val sessionOngoing: Boolean
        get() = _session.value != null

    init {
        if (sessionId != null) resumeSession(sessionId)
        else startSession()
    }

    private fun startSession() {
        if (sessionOngoing) return
        _session.update { sessionDataSource.insertAndReturnSession(Session()) }
    }

    private fun resumeSession(id: Int) {
        if (sessionOngoing) return
        _session.update { sessionDataSource.getSessionById(id) }
        getSessionDrinkEvents()
    }

    private val _drinkEvents = MutableStateFlow<List<DrinkEvent>>(emptyList())
    val drinkEvents = _drinkEvents.asStateFlow()

    fun addDrinkEvent(drinkEvent: DrinkEvent) {
        if (!sessionOngoing) return
        _drinkEvents.update { it + drinkEventDataSource.insertAndReturnDrinkEvent(drinkEvent) }
    }

    fun deleteDrinkEvent(drinkEvent: DrinkEvent) {
        if (!sessionOngoing) return
        drinkEventDataSource.deleteDrinkEvent(drinkEvent)
        _drinkEvents.update { it - drinkEvent }
    }

    private fun getSessionDrinkEvents() {
        if (!sessionOngoing) return
        _drinkEvents.value = drinkEventDataSource.getDrinkEventsForSession(session.value!!.id!!)
    }
}