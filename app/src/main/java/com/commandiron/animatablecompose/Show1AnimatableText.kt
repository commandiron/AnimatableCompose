package com.commandiron.animatablecompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.commandiron.animatable_compose.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableTextState

@Composable
fun Show1AnimatableText() {
    // Simply create state and pass it to AnimatableText
    val state = rememberAnimatableTextState(
        initialFontSize = 12.sp,
        targetFontSize = 60.sp
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                state.animate() // animate
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatableText(
            text = "Animatable",
            state = state // pass state
        )
        AnimatableText(
            text = "Compose",
            state = state // pass state
        )
    }
}