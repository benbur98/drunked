package di

import android.content.Context
import com.drunked.drunked.database.DrunkedDatabase
import data.DatabaseDriverFactory
import data.initDatabase


actual class AppModule(
    private val context: Context
) {
    actual val database: DrunkedDatabase by lazy {
        initDatabase(driverFactory = DatabaseDriverFactory(context))
    }
}