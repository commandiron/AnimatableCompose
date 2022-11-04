package com.commandiron.animatable_compose

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimatableStateTag
import com.commandiron.animatable_compose.state.SharedAnimatableState

@Composable
fun AnimatableLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    state: AnimatableState,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
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
        state = lazyListState,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = content
    )
}

@Composable
fun AnimatableLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    state: SharedAnimatableState,
    stateIndex: Int = 0,
    fixedWidth: Dp = Dp.Unspecified,
    fixedHeight: Dp = Dp.Unspecified,
    fixedOffset: DpOffset = DpOffset.Unspecified,
    content: LazyListScope.() -> Unit
) {
    val stateIn = state.getState(AnimatableStateTag.LAZY_COLUMN, stateIndex) ?: throw (
            IllegalArgumentException("no animatableState has this index: $stateIndex")
            )
    LazyColumn(
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
        state = lazyListState,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = content
    )
}