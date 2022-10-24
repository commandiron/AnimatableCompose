package com.commandiron.animatable_compose.state

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatableCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    border: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    colors: CardColors = CardDefaults.cardColors(),
    state: AnimatableState,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedShape: Shape? = null,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier
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
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        shape = fixedShape ?: state.animatedShape,
        border = border,
        elevation = elevation,
        colors = colors,
        content = content
    )
}


