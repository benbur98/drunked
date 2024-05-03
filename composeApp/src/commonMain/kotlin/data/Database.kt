package data

import com.drunked.drunked.database.DrunkedDatabase
import data.drink.datasources.DrinkDataSource
import data.drink.datasources.DrinkEventDataSource
import data.drink.datasources.SessionDataSource


const val DATABASE_NAME = "drunked.db"


fun initDatabase(driverFactory: DatabaseDriverFactory): DrunkedDatabase {
    val driver = driverFactory.createDriver()

    return DrunkedDatabase(
        driver,
        DrinkAdapter = DrinkDataSource.drinkAdapter,
        DrinkEventAdapter = DrinkEventDataSource.drinkEventAdapter,
        SessionAdapter = SessionDataSource.sessionAdapter
    )
}