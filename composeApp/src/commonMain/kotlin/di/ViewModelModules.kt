package di

import DrinkViewModel
import SessionViewModel
import org.koin.dsl.module


val viewModelModules = module {
    factory { DrinkViewModel(get()) }
    factory { SessionViewModel(get()) }
}