package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.AnimatableIcon
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import com.commandiron.animatable_compose.state.rememberAnimatableIconState
import com.commandiron.animatable_compose.state.rememberAnimatableTextState

@Composable
fun Show4() {
    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(80.dp, 80.dp),
        targetSize = DpSize(Dp.Infinity, 100.dp),
        toTargetSizeAnimationSpec = tween(500, 500),
        initialShape = RoundedCornerShape(32.dp),
        targetShape = RoundedCornerShape(0.dp),
        toTargetShapeAnimationSpec = tween(500, 500),
        initialAlpha = 0.85f,
        targetAlpha = 1f,
        toTargetAlphaAnimationSpec = tween(500, 500),
        initialOffset = DpOffset(0.dp, 0.dp),
        targetOffset = DpOffset(0.dp, - Dp.Infinity),
        toInitialOffsetAnimationSpec = tween(500, 500),
    )
    val animatableIconState = rememberAnimatableIconState(
        initialSize = DpSize(40.dp, 40.dp),
        targetSize = DpSize(80.dp, 80.dp),
        toTargetSizeAnimationSpec = tween(500,500),
        initialOffset = DpOffset(0.dp, 0.dp),
        targetOffset = DpOffset((-50).dp, 0.dp),
        toTargetOffsetAnimationSpec = tween(500, 500)
    )
    val animatableIconState2 = animatableIconState.copy(
        initialSize = DpSize(0.dp, 0.dp),
        targetSize = DpSize(36.dp, 36.dp),
        targetOffset = DpOffset(40.dp, 0.dp),
    )
    val animatableTextState = rememberAnimatableTextState(
        initialFontSize = 0.sp,
        targetFontSize = 26.sp,
        toTargetFontSizeAnimationSpec = tween(500, 500),
        initialOffset = DpOffset(0.dp, 0.dp),
        targetOffset = DpOffset((-25).dp, 0.dp),
        toTargetOffsetAnimationSpec = tween(500, 500)
    )
    val animatableTextState2 = animatableTextState.copy(
        targetFontSize = 12.sp
    )
    AnimatableCard(
        onClick = {
            animatableCardState.animate()
            animatableIconState.animate()
            animatableIconState2.animate()
            animatableTextState.animate()
            animatableTextState2.animate()
        },
        state = animatableCardState
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatableIcon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                state = animatableIconState
            )
            Column() {
                AnimatableText(
                    text = "Emir Demirli",
                    state = animatableTextState
                )
                AnimatableText(
                    text = "+90 535 508 55 52",
                    state = animatableTextState2
                )
            }
            AnimatableIcon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                state = animatableIconState2
            )
        }
    }
}