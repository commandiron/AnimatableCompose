package com.commandiron.animatable_compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimatableStateTag
import com.commandiron.animatable_compose.state.SharedAnimatableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatableCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    fixedShape: Shape? = null,
    fixedBorder: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    colors: CardColors = CardDefaults.cardColors(),
    state: AnimatableState,
    fixedPadding: PaddingValues = PaddingValues(0.dp),
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    @FloatRange(from = 0.0, to = 1.0)
    fixedAlpha: Float? = null,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(
                when (fixedPadding) {
                    PaddingValues(0.dp) ->  state.animatedPadding
                    else -> fixedPadding
                }
            )
            .width(
                when (fixedWidth) {
                    Dp.Unspecified -> if (state.animatedSize == DpSize.Unspecified) {
                        Dp.Unspecified
                    } else state.animatedSize.width
                    Dp.Infinity -> state.screenWidth
                    else -> fixedWidth
                }
            )
            .height(
                when (fixedHeight) {
                    Dp.Unspecified -> if (state.animatedSize == DpSize.Unspecified) {
                        Dp.Unspecified
                    } else state.animatedSize.height
                    Dp.Infinity -> state.screenHeight
                    else -> fixedHeight
                }
            )
            .offset(
                x = when (fixedOffset) {
                    DpOffset.Unspecified -> state.animatedOffset.x
                    else -> fixedOffset.x
                },
                y = when (fixedOffset) {
                    DpOffset.Unspecified -> state.animatedOffset.y
                    else -> fixedOffset.y
                }
            )
            .alpha(fixedAlpha ?: state.animatedAlpha)
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        shape = fixedShape ?: state.animatedShape,
        border = fixedBorder ?: state.animatedBorder,
        elevation = elevation,
        colors = colors,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatableCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    fixedShape: Shape? = null,
    fixedBorder: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    colors: CardColors = CardDefaults.cardColors(),
    state: SharedAnimatableState,
    stateIndex: Int = 0,
    fixedPadding: PaddingValues = PaddingValues(0.dp),
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    @FloatRange(from = 0.0, to = 1.0)
    fixedAlpha: Float? = null,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable ColumnScope.() -> Unit
) {
    val stateIn = state.getState(AnimatableStateTag.CARD, stateIndex) ?: throw (
            IllegalArgumentException("no animatableState has this index: $stateIndex")
    )
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(
                when (fixedPadding) {
                    PaddingValues(0.dp) ->  stateIn.animatedPadding
                    else -> fixedPadding
                }
            )
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
            .offset(
                x = when (fixedOffset) {
                    DpOffset.Unspecified -> stateIn.animatedOffset.x
                    else -> fixedOffset.x
                },
                y = when (fixedOffset) {
                    DpOffset.Unspecified -> stateIn.animatedOffset.y
                    else -> fixedOffset.y
                }
            )
            .alpha(fixedAlpha ?: stateIn.animatedAlpha)
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        shape = fixedShape ?: stateIn.animatedShape,
        border = fixedBorder ?: stateIn.animatedBorder,
        elevation = elevation,
        colors = colors,
        content = content
    )
}


