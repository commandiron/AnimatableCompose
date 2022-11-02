package com.commandiron.animatablecompose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.AnimatableBox
import com.commandiron.animatable_compose.state.rememberAnimatableBoxState

@Composable
fun Show2AnimatableBox() {
    val state = rememberAnimatableBoxState(
        initialSize = DpSize(60.dp, 60.dp), // set initial size
        targetSize = DpSize(Dp.Infinity, 120.dp), // set target size
        initialOffset = DpOffset(x = 0.dp, y = 0.dp), // set initial offset
        targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity), // set target offset
        initialAlignment = Alignment.Center,
        targetAlignment = Alignment.TopStart
        // Dp.Infinity will take the maximum value according to the screen size
    )
    AnimatableBox(
        modifier = Modifier
            .border(1.dp, Color.Red)
            .clickable {
                state.animate()
            },
        state = state
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}