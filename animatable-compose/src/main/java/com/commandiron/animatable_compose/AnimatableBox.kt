package com.commandiron.animatable_compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimatableStateTag
import com.commandiron.animatable_compose.state.SharedAnimatableState

@Composable
fun AnimatableBox(
    modifier: Modifier = Modifier,
    fixedContentAlignment: Alignment? = null,
    propagateMinConstraints: Boolean = false,
    state: SharedAnimatableState,
    stateIndex: Int = 0,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedBorder: BorderStroke? = null,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    val stateIn = state.getState(AnimatableStateTag.BOX, stateIndex) ?: throw (
        IllegalArgumentException("no animatableState has this index: $stateIndex")
    )
    Box(
        modifier = Modifier
            .width(
                when (fixedWidth) {
                    Dp.Unspecified -> if (stateIn.animatedSize == DpSize.Unspecified) {
                        Dp.Unspecified
                    } else stateIn.animatedSize.width
                    Dp.Infinity -> stateIn.screenWidth
                    else -> fixedWidth
                }
            )
            .height(
                when (fixedHeight) {
                    Dp.Unspecified -> if (stateIn.animatedSize == DpSize.Unspecified) {
                        Dp.Unspecified
                    } else stateIn.animatedSize.height
                    Dp.Infinity -> stateIn.screenHeight
                    else -> fixedHeight
                }
            )
            .border(fixedBorder ?: stateIn.animatedBorder)
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
        contentAlignment = fixedContentAlignment ?: stateIn.animatedAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        content()
    }
}

@Composable
fun AnimatableBox(
    modifier: Modifier = Modifier,
    fixedContentAlignment: Alignment? = null,
    propagateMinConstraints: Boolean = false,
    state: AnimatableState,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedBorder: BorderStroke? = null,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
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
            .border(fixedBorder ?: state.animatedBorder)
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
        contentAlignment = fixedContentAlignment ?: state.animatedAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        content()
    }
}