package com.commandiron.animatablecompose

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableTextState
import kotlinx.coroutines.launch

@Composable
fun Show3() {
    val animatableTextState = rememberAnimatableTextState(
        initialFontSize = 12.sp,
        targetFontSize = 72.sp
    )
    val scope = rememberCoroutineScope()
    AnimatableText(
        modifier = Modifier
            .clickable {
                scope.launch {
                    animatableTextState.animate()
                }
        },
        text = "Animatable",
        state = animatableTextState
    )
}