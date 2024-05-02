package data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.drunked.drunked.database.DrunkedDatabase


actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver =
        NativeSqliteDriver(DrunkedDatabase.Schema, DATABASE_NAME)
}