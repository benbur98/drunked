package data

import org.koin.dsl.module


val databaseModule = module {
    single {
        initDatabase(driverFactory = get())
    }
}