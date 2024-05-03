package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun DrinkCount(currentValue: Int, maxValue: Int) {
    val fillFraction = currentValue / maxValue

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = fillFraction.toFloat())
                .background(Color.Blue)
                .align(Alignment.BottomCenter)
        )
        Text(
            text = currentValue.toString(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}