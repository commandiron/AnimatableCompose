package com.commandiron.animatablecompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableTextState

@Composable
fun Show1() {
    val state = rememberAnimatableTextState(
        initialFontSize = 12.sp,
        targetFontSize = 72.sp
    )
    Column(
        modifier = Modifier.clickable {
            state.animate()
        }
    ) {
        AnimatableText(
            text = "Animatable",
            state = state
        )
        AnimatableText(
            text = "Compose",
            state = state
        )
    }

}