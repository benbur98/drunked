package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import drunked.composeapp.generated.resources.Res
import drunked.composeapp.generated.resources.drunked
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun Logo() {
    Image(
        painter = painterResource(Res.drawable.drunked),
        contentDescription = "drunked Logo",
        modifier = Modifier.width(250.dp)
    )
}