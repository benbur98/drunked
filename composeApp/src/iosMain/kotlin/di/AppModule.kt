package di

import com.drunked.drunked.database.DrunkedDatabase
import data.DatabaseDriverFactory
import data.initDatabase


actual class AppModule {
    actual val database: DrunkedDatabase by lazy {
        initDatabase(driverFactory = DatabaseDriverFactory())
    }
}