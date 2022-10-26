package com.commandiron.animatablecompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.state.rememberAnimatableCardState

@Composable
fun Show3() {
    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 70.dp, height = 70.dp),
        targetSize = DpSize(width = 200.dp, height = 70.dp),
        initialShape = CircleShape,
        targetShape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 0.dp),
        initialOffset = DpOffset(x = 0.dp, y = 0.dp),
        targetOffset = DpOffset(x = - Dp.Infinity, y = - Dp.Infinity)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                animatableCardState.animateToInitial() // animate to initial
            },
        contentAlignment = Alignment.Center
    ) {
        AnimatableCard(
            modifier = Modifier.size(100.dp),
            onClick = {
                animatableCardState.animateToTarget() // animate to target
            },
            state = animatableCardState
        ) {}
    }
}