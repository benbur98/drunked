package di

import DrinkViewModel
import SessionRecordViewModel
import org.koin.dsl.module


val viewModelModules = module {
    factory { DrinkViewModel(get()) }
    factory { SessionRecordViewModel(get()) }
}