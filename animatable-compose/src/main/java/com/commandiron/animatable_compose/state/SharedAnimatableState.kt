package com.commandiron.animatable_compose.state

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberSharedAnimatableState(
    animatableStates: List<AnimatableState>,
    toTargetAnimationSpec: AnimationSpec<Float>? = null,
    toInitialAnimationSpec: AnimationSpec<Float>? = null,
): SharedAnimatableState {
    return remember {
        SharedAnimatableState(
            animatableStates = animatableStates,
            toTargetAnimationSpec = toTargetAnimationSpec,
            toInitialAnimationSpec = toInitialAnimationSpec
        )
    }
}

data class SharedAnimatableState(
    private var animatableStates: List<AnimatableState>,
    private val toTargetAnimationSpec: AnimationSpec<Float>? = null,
    private val toInitialAnimationSpec: AnimationSpec<Float>? = null,
) {
    private val states by mutableStateOf(
        if(toTargetAnimationSpec != null && toInitialAnimationSpec != null) {
            animatableStates.map {
                it.copy(
                    toTargetAnimationSpec = toTargetAnimationSpec,
                    toInitialAnimationSpec = toInitialAnimationSpec
                )
            }
        } else {
            if(toTargetAnimationSpec != null && toInitialAnimationSpec == null) {
                animatableStates.map {
                    it.copy(
                        toTargetAnimationSpec = toTargetAnimationSpec
                    )
                }
            } else if(toTargetAnimationSpec == null && toInitialAnimationSpec != null) {
                animatableStates.map {
                    it.copy(
                        toTargetAnimationSpec = toTargetAnimationSpec
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