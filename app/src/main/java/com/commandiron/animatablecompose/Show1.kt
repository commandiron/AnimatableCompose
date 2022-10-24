package com.commandiron.animatablecompose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.AnimatableBox
import com.commandiron.animatable_compose.state.rememberAnimatableBoxState
import kotlinx.coroutines.launch

@Composable
fun Show1() {
    val animatableBoxState = rememberAnimatableBoxState(
        initialSize = DpSize(60.dp, 60.dp),
        targetSize = DpSize(Dp.Infinity, 120.dp),
        initialOffset = DpOffset(x = 0.dp, y = 0.dp),
        targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity)
    )
    val scope = rememberCoroutineScope()
    AnimatableBox(
        modifier = Modifier
            .border(1.dp, Color.Red)
            .clickable {
                scope.launch {
                    animatableBoxState.transform()
                }
            },
        state = animatableBoxState
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}