package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.state.AnimatableCard
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import kotlinx.coroutines.launch

@Composable
fun Show2() {
    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 70.dp, height = 70.dp),
        targetSize = DpSize(width = 200.dp, height = 70.dp),
        initialShape = CircleShape,
        targetShape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 0.dp),
        initialOffset = DpOffset(x = 0.dp, y = 0.dp),
        targetOffset = DpOffset(x = - Dp.Infinity, y = -Dp.Infinity),
        offsetAnimationSpec = tween(1000)
    )
    val scope = rememberCoroutineScope()
    AnimatableCard(
        modifier = Modifier.size(100.dp),
        onClick = {
            scope.launch {
                animatableCardState.transform()
            }
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        state = animatableCardState
    ) {}
}