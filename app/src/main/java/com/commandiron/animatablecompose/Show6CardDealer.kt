package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import com.commandiron.animatable_compose.state.rememberAnimatableTextState
import com.commandiron.animatable_compose.state.rememberSharedAnimatableState

@Composable
fun Show6CardDealer() {

    val cards by remember  { 
        mutableStateOf(listOf("A","K","Q","J","10","9","8","7","6","5","4","3","2"))
    }
    var deck by remember {
        mutableStateOf(cards + cards + cards + cards)
    }

    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(64.dp, 64.dp),
        targetSize = DpSize(64.dp, 64.dp),
        initialOffset = DpOffset(0.dp, 120.dp),
        targetOffset = DpOffset(-Dp.Infinity, -Dp.Infinity)
    )
    val animatableTextState = rememberAnimatableTextState(
        initialFontSize = 0.sp,
        targetFontSize = 24.sp
    )

    val cardStates = mutableListOf<AnimatableState>()
    val textStates = mutableListOf<AnimatableState>()

    deck.indices.forEach { index ->
        cardStates.add(
            animatableCardState.copy(
                index = index,
                toTargetOffsetAnimationSpec = tween(400, (index * 400)),
                targetOffset = DpOffset(if(index % 2 == 0) (-100).dp else 100.dp, (-150).dp)
            )
        )
        textStates.add(
            animatableTextState.copy(
                index = index,
                toTargetFontSizeAnimationSpec = tween(400, (index * 400))
            )
        )

    }
    
    val sharedAnimatableState = rememberSharedAnimatableState(cardStates + textStates)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                deck = deck.shuffled()
                sharedAnimatableState.animate()
            },
        contentAlignment = Alignment.Center
    ) {
        deck.indices.forEach { index ->
            AnimatableCard(
                onClick = {},
                state = sharedAnimatableState,
                stateIndex = index,
                fixedShape = RoundedCornerShape(16.dp)
            ) {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    AnimatableText(
                        text = deck[index],
                        state = sharedAnimatableState,
                        stateIndex = index
                    )
                }
            }
        }
    }
}