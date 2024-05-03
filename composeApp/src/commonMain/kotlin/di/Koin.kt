package di

import data.databaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            databaseModule,
//            viewModelModules,
            platformModule()
        )
    }
}


fun initKoin() = initKoin {}