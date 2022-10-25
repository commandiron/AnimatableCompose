package com.commandiron.animatable_compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.commandiron.animatable_compose.state.AnimatableStateTag
import com.commandiron.animatable_compose.state.SharedAnimatableState

@Composable
fun AnimatableIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    state: SharedAnimatableState,
    stateIndex: Int = 0,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedOffset: DpOffset = DpOffset.Unspecified,
) {
    val stateIn = state.getState(AnimatableStateTag.ICON, stateIndex) ?: throw (
            IllegalArgumentException("no animatableState has this index: $stateIndex")
    )
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
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
            .then(modifier),
        tint = tint
    )
}