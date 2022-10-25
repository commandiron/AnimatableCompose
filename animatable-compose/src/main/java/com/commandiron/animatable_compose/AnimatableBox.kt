package com.commandiron.animatable_compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimatableStateTag
import com.commandiron.animatable_compose.state.SharedAnimatableState

@Composable
fun AnimatableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    state: SharedAnimatableState,
    stateIndex: Int = 0,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    val stateIn = state.getState(AnimatableStateTag.CARD, stateIndex) ?: throw (
        IllegalArgumentException("no animatableState has this index: $stateIndex")
    )
    BoxWithConstraints(
        modifier = Modifier
            .width(
                when(fixedWidth) {
                    Dp.Unspecified -> stateIn.animatedSize.width
                    Dp.Infinity -> stateIn.screenWidth
                    else -> fixedWidth
                }
            )
            .height(
                when(fixedHeight) {
                    Dp.Unspecified -> stateIn.animatedSize.height
                    Dp.Infinity -> stateIn.screenHeight
                    else -> fixedHeight
                }
            )
            .offset(
                x = when(fixedOffset) {
                    DpOffset.Unspecified -> stateIn.animatedOffset.x
                    else -> fixedOffset.x
                },
                y = when(fixedOffset) {
                    DpOffset.Unspecified -> stateIn.animatedOffset.y
                    else -> fixedOffset.y
                }
            )
            .then(modifier),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        content()
    }
}

@Composable
fun AnimatableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    state: AnimatableState,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .width(
                when(fixedWidth) {
                    Dp.Unspecified -> state.animatedSize.width
                    Dp.Infinity -> state.screenWidth
                    else -> fixedWidth
                }
            )
            .height(
                when(fixedHeight) {
                    Dp.Unspecified -> state.animatedSize.height
                    Dp.Infinity -> state.screenHeight
                    else -> fixedHeight
                }
            )
            .offset(
                x = when(fixedOffset) {
                    DpOffset.Unspecified -> state.animatedOffset.x
                    else -> fixedOffset.x
                },
                y = when(fixedOffset) {
                    DpOffset.Unspecified -> state.animatedOffset.y
                    else -> fixedOffset.y
                }
            )
            .then(modifier),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        content()
    }
}