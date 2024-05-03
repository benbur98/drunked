package data.drink.datasources

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.Session


class SessionDataSource(private val database: DrunkedDatabase) {
    private val sessionQueries = database.sessionQueries

    private fun sessionMapper(id: Int, date: String): Session =
        Session(date = date, id = id)

    fun getSessionById(id: Int): Session {
        return sessionQueries.selectById(id, ::sessionMapper).executeAsOne()
    }

    fun getAllSessions(): List<Session> {
        return sessionQueries.selectAll(::sessionMapper).executeAsList()
    }

    fun insertSession(session: Session) {
        sessionQueries.insert(date = session.date)
    }

    fun insertAndReturnSession(session: Session): Session {
        var insertedSession: Session? = null
        database.transaction {
            sessionQueries.insert(date = session.date)
            val lastInsertId = sessionQueries.lastInsertId().executeAsOne()
            insertedSession = sessionQueries.selectById(lastInsertId.toInt(), ::sessionMapper).executeAsOne()
        }
        return insertedSession!!
    }

    companion object {
        val sessionAdapter = com.drunked.drunked.database.Session.Adapter(
            idAdapter = IntColumnAdapter,
        )
    }
}
