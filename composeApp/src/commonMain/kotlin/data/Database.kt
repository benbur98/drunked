package data

import app.cash.sqldelight.db.SqlDriver


const val DATABASE_NAME = "drunked.db"


expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}