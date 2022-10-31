package com.commandiron.animatablecompose

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import com.commandiron.animatable_compose.state.rememberAnimatableTextState
import com.commandiron.animatable_compose.state.rememberSharedAnimatableState

@Composable
fun Show4AnimatableCardWithText() {
    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 50.dp, height = 25.dp),
        targetSize = DpSize(width = 300.dp, height = 150.dp),
        toTargetSizeAnimationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow),
        initialShape =  RoundedCornerShape(4.dp),
        targetShape = RoundedCornerShape(16.dp),
        toTargetShapeAnimationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow),
    )
    val animatableTextState = rememberAnimatableTextState(
        initialFontSize = 4.sp,
        targetFontSize = 36.sp,
        toTargetFontSizeAnimationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow)
    )
    val sharedAnimatableState = rememberSharedAnimatableState(
        listOf(
            animatableCardState,
            animatableTextState
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { sharedAnimatableState.animate() },
        contentAlignment = Alignment.Center
    ) {
        AnimatableCard(
            modifier = Modifier.size(100.dp),
            state = sharedAnimatableState
        ) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                AnimatableText(
                    text = "Animatable",
                    state = sharedAnimatableState
                )
            }
        }
    }
}