package di

import com.drunked.drunked.database.DrunkedDatabase


expect class AppModule {
    val database: DrunkedDatabase
}