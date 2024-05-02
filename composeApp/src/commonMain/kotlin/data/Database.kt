package data

import app.cash.sqldelight.db.SqlDriver
import com.drunked.drunked.database.DrunkedDatabase
import data.drink.datasources.DrinkDataSource
import data.drink.datasources.DrinkEventDataSource


const val DATABASE_NAME = "drunked.db"


expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}


fun createDatabase(driverFactory: DatabaseDriverFactory): DrunkedDatabase {
    val driver = driverFactory.create()

    return DrunkedDatabase(
        driver,
        DrinkAdapter = DrinkDataSource.drinkAdapter,
        DrinkEventAdapter = DrinkEventDataSource.drinkEventAdapter
    )
}