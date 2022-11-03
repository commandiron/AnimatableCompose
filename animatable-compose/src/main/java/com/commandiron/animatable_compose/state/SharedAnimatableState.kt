package com.commandiron.animatable_compose.state

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberSharedAnimatableState(
    animatableStates: List<AnimatableState>,
    toTargetAnimationsSpec: AnimationSpec<Float>? = null,
    toInitialAnimationsSpec: AnimationSpec<Float>? = null
): SharedAnimatableState {
    return remember {
        SharedAnimatableState(
            animatableStates = animatableStates,
            toTargetAnimationsSpec = toTargetAnimationsSpec,
            toInitialAnimationsSpec = toInitialAnimationsSpec
        )
    }
}

data class SharedAnimatableState(
    private var animatableStates: List<AnimatableState>,
    private val toTargetAnimationsSpec: AnimationSpec<Float>? = null,
    private val toInitialAnimationsSpec: AnimationSpec<Float>? = null
) {
    private val states by mutableStateOf(
        if(toTargetAnimationsSpec != null && toInitialAnimationsSpec != null) {
            animatableStates.map {
                it.copy(
                    toTargetAnimationSpec = toTargetAnimationsSpec,
                    toInitialAnimationSpec = toInitialAnimationsSpec
                )
            }
        } else {
            if(toTargetAnimationsSpec != null && toInitialAnimationsSpec == null) {
                animatableStates.map {
                    it.copy(
                        toTargetAnimationSpec = toTargetAnimationsSpec
                    )
                }
            } else if(toTargetAnimationsSpec == null && toInitialAnimationsSpec != null) {
                animatableStates.map {
                    it.copy(
                        toInitialAnimationSpec = toInitialAnimationsSpec
                    )
                }
            } else {
                animatableStates
            }
        }
    )

    fun getState(
        animatableStateTag: AnimatableStateTag,
        index: Int
    ): AnimatableState? {
        val filteredStates = states.filter { it.animatableStateTag == animatableStateTag }
        return filteredStates.getOrNull(index)
    }

    fun animate() {
        states.forEach { it.animate() }
    }

    fun animateToTarget() {
        animatableStates.forEach { it.animateToTarget() }
    }
    fun animateToInitial() {
        animatableStates.forEach { it.animateToInitial() }
    }
}