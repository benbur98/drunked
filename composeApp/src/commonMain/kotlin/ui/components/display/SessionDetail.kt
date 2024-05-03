package ui.components.display

import androidx.compose.runtime.Composable
import data.drink.Session
import data.drink.datasources.DrinkEventDataSource


@Composable
fun SessionDetail(session: Session, drinkEventDataSource: DrinkEventDataSource) {
    val drinkEvents = drinkEventDataSource.getDrinkEventsForSession(session.id!!)

    SessionCard(session)

    DrinkEventList(drinkEvents)
}