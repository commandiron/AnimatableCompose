package com.commandiron.animatable_compose.state

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*

@Composable
fun rememberSharedAnimatableState(
    animatableStates: List<AnimatableState>
): SharedAnimatableState {
    return remember {
        SharedAnimatableState(
            animatableStates = animatableStates
        )
    }
}

data class SharedAnimatableState(
    private val animatableStates: List<AnimatableState>
) {
    fun getState(
        animatableStateTag: AnimatableStateTag,
        index: Int
    ): AnimatableState? {
        val states = animatableStates.filter { it.animatableStateTag == animatableStateTag }
        return states.getOrNull(index)
    }

    fun animate() {
        animatableStates.forEach { it.animate() }
    }

    fun animateToTarget() {
        animatableStates.forEach { it.animateToTarget() }
    }
    fun animateToInitial() {
        animatableStates.forEach { it.animateToInitial() }
    }
}

@Composable
fun rememberAnimatableTextState(
    index: Int = 0,
    initialFontSize: TextUnit? = null,
    targetFontSize: TextUnit? = null,
    toTargetFontSizeAnimationSpec: AnimationSpec<Float> = tween(500),
    toInitialFontSizeAnimationSpec: AnimationSpec<Float> = tween(500),
    onFontSizeAnimation: (AnimationState) -> Unit = {},
    initialAlpha: Float? = null,
    targetAlpha: Float? = null,
    toTargetAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    toInitialAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    onAlphaAnimation: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    toTargetOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetAnimation: (AnimationState) -> Unit = {}
): AnimatableState {
    return remember {
        AnimatableState(
            animatableStateTag = AnimatableStateTag.TEXT,
            index = index,
            initialFontSize = initialFontSize,
            targetFontSize = targetFontSize,
            toTargetFontSizeAnimationSpec = toTargetFontSizeAnimationSpec,
            toInitialFontSizeAnimationSpec = toInitialFontSizeAnimationSpec,
            onFontSizeAnimation = onFontSizeAnimation,
            initialAlpha = initialAlpha,
            targetAlpha = targetAlpha,
            toTargetAlphaAnimationSpec = toTargetAlphaAnimationSpec,
            toInitialAlphaAnimationSpec = toInitialAlphaAnimationSpec,
            onAlphaAnimation = onAlphaAnimation,
            initialOffset = initialOffset,
            targetOffset = targetOffset,
            toTargetOffsetAnimationSpec = toTargetOffsetAnimationSpec,
            toInitialOffsetAnimationSpec = toInitialOffsetAnimationSpec,
            onOffsetAnimation = onOffsetAnimation
        )
    }
}

@Composable
fun rememberAnimatableBoxState(
    index: Int = 0,
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    toTargetSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeAnimation: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    toTargetOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetAnimation: (AnimationState) -> Unit = {},
): AnimatableState {
    return remember {
        AnimatableState(
            animatableStateTag = AnimatableStateTag.BOX,
            index = index,
            initialSize = initialSize,
            targetSize = targetSize,
            toTargetSizeAnimationSpec = toTargetSizeAnimationSpec,
            toInitialSizeAnimationSpec = toInitialSizeAnimationSpec,
            onSizeAnimation = onSizeAnimation,
            initialOffset = initialOffset,
            targetOffset = targetOffset,
            toTargetOffsetAnimationSpec = toTargetOffsetAnimationSpec,
            toInitialOffsetAnimationSpec = toInitialOffsetAnimationSpec,
            onOffsetAnimation = onOffsetAnimation
        )
    }
}

@Composable
fun rememberAnimatableCardState(
    index: Int = 0,
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    toTargetSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeAnimation: (AnimationState) -> Unit = {},
    initialShape: Shape? = null,
    targetShape: Shape? = null,
    toTargetShapeAnimationSpec: AnimationSpec<Float> = tween(500),
    toInitialShapeAnimationSpec: AnimationSpec<Float> = tween(500),
    onShapeAnimation: (AnimationState) -> Unit = {},
    initialAlpha: Float? = null,
    targetAlpha: Float? = null,
    toTargetAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    toInitialAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    onAlphaAnimation: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    toTargetOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetAnimation: (AnimationState) -> Unit = {},
): AnimatableState {
    return remember {
        AnimatableState(
            animatableStateTag = AnimatableStateTag.CARD,
            index = index,
            initialSize = initialSize,
            targetSize = targetSize,
            toTargetSizeAnimationSpec = toTargetSizeAnimationSpec,
            toInitialSizeAnimationSpec = toInitialSizeAnimationSpec,
            onSizeAnimation = onSizeAnimation,
            initialShape = initialShape,
            targetShape = targetShape,
            toTargetShapeAnimationSpec = toTargetShapeAnimationSpec,
            toInitialShapeAnimationSpec = toInitialShapeAnimationSpec,
            onShapeAnimation = onShapeAnimation,
            initialAlpha = initialAlpha,
            targetAlpha = targetAlpha,
            toTargetAlphaAnimationSpec = toTargetAlphaAnimationSpec,
            toInitialAlphaAnimationSpec = toInitialAlphaAnimationSpec,
            onAlphaAnimation = onAlphaAnimation,
            initialOffset = initialOffset,
            targetOffset = targetOffset,
            toTargetOffsetAnimationSpec = toTargetOffsetAnimationSpec,
            toInitialOffsetAnimationSpec = toInitialOffsetAnimationSpec,
            onOffsetAnimation = onOffsetAnimation
        )
    }
}

@Composable
fun rememberAnimatableIconState(
    index: Int = 0,
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    toTargetSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialSizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeAnimation: (AnimationState) -> Unit = {},
    initialAlpha: Float? = null,
    targetAlpha: Float? = null,
    toTargetAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    toInitialAlphaAnimationSpec: AnimationSpec<Float> = tween(500),
    onAlphaAnimation: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    toTargetOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    toInitialOffsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetAnimation: (AnimationState) -> Unit = {},
): AnimatableState {
    return remember {
        AnimatableState(
            animatableStateTag = AnimatableStateTag.ICON,
            index = index,
            initialSize = initialSize,
            targetSize = targetSize,
            toTargetSizeAnimationSpec = toTargetSizeAnimationSpec,
            toInitialSizeAnimationSpec = toInitialSizeAnimationSpec,
            initialAlpha = initialAlpha,
            targetAlpha = targetAlpha,
            toTargetAlphaAnimationSpec = toTargetAlphaAnimationSpec,
            toInitialAlphaAnimationSpec = toInitialAlphaAnimationSpec,
            onAlphaAnimation = onAlphaAnimation,
            onSizeAnimation = onSizeAnimation,
            initialOffset = initialOffset,
            targetOffset = targetOffset,
            toTargetOffsetAnimationSpec = toTargetOffsetAnimationSpec,
            toInitialOffsetAnimationSpec = toInitialOffsetAnimationSpec,
            onOffsetAnimation = onOffsetAnimation
        )
    }
}

data class AnimatableState(
    val animatableStateTag: AnimatableStateTag,
    val index: Int,

    private val initialSize: DpSize? = null,
    private val targetSize: DpSize? = null,
    private val toTargetSizeAnimationSpec: AnimationSpec<Size>? = null,
    private val toInitialSizeAnimationSpec: AnimationSpec<Size>? = null,
    private val onSizeAnimation: (AnimationState) -> Unit = {},

    private val initialShape: Shape? = null,
    private val targetShape: Shape? = null,
    private val toTargetShapeAnimationSpec: AnimationSpec<Float>? = null,
    private val toInitialShapeAnimationSpec: AnimationSpec<Float>? = null,
    private val onShapeAnimation: (AnimationState) -> Unit = {},

    private val initialAlpha: Float? = null,
    private val targetAlpha: Float? = null,
    private val toTargetAlphaAnimationSpec: AnimationSpec<Float>? = null,
    private val toInitialAlphaAnimationSpec: AnimationSpec<Float>? = null,
    private val onAlphaAnimation: (AnimationState) -> Unit = {},

    private val initialOffset: DpOffset? = null,
    private val targetOffset: DpOffset? = null,
    private val toTargetOffsetAnimationSpec: AnimationSpec<Size>? = null,
    private val toInitialOffsetAnimationSpec: AnimationSpec<Size>? = null,
    private val onOffsetAnimation: (AnimationState) -> Unit = {},

    private val initialFontSize: TextUnit? = null,
    private val targetFontSize: TextUnit? = null,
    private val toTargetFontSizeAnimationSpec: AnimationSpec<Float>? = null,
    private val toInitialFontSizeAnimationSpec: AnimationSpec<Float>? = null,
    private val onFontSizeAnimation: (AnimationState) -> Unit = {},

    private val onAnimation: (AnimationState) -> Unit = {},
) {
    private var size by mutableStateOf(initialSize)
    private var sizeAnimationSpec by mutableStateOf(toTargetSizeAnimationSpec)
    private var sizeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setSizeAnim(animationState: AnimationState) {
        sizeAnimState = animationState
        onSizeAnimation(animationState)
        calculateSharedAnimationState()
    }

    private var shape by mutableStateOf(initialShape)
    private var shapeAnimationSpec by mutableStateOf(toTargetShapeAnimationSpec)
    private var shapeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setShapeAnim(animationState: AnimationState) {
        shapeAnimState = animationState
        onShapeAnimation(animationState)
        calculateSharedAnimationState()
    }

    private var alpha by mutableStateOf(initialAlpha)
    private var alphaAnimationSpec by mutableStateOf(toTargetAlphaAnimationSpec)
    private var alphaAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setAlphaAnim(animationState: AnimationState) {
        alphaAnimState = animationState
        onAlphaAnimation(animationState)
        calculateSharedAnimationState()
    }

    private var offset by mutableStateOf(initialOffset)
    private var offsetAnimationSpec by mutableStateOf(toTargetOffsetAnimationSpec)
    private var offsetAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setOffsetAnim(animationState: AnimationState) {
        offsetAnimState = animationState
        onOffsetAnimation(animationState)
        calculateSharedAnimationState()
    }

    private var fontSize by  mutableStateOf(initialFontSize)
    private var fontSizeAnimationSpec by mutableStateOf(toTargetFontSizeAnimationSpec)
    private var fontSizeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setFontSizeAnim(animationState: AnimationState) {
        fontSizeAnimState = animationState
        onFontSizeAnimation(animationState)
        calculateSharedAnimationState()
    }

    internal val screenHeight: Dp
        @Composable
        get() {
            return Dp(LocalConfiguration.current.screenHeightDp.toFloat())
        }

    internal val screenWidth: Dp
        @Composable
        get() {
            return Dp(LocalConfiguration.current.screenWidthDp.toFloat())
        }

    fun animate() {

        if(size != null) {
            animateSize()
        }

        if(shape != null) {
            animateShape()
        }

        if(alpha != null) {
            animateAlpha()
        }

        if(offset != null) {
            animateOffset()
        }

        if(fontSize != null) {
            animateFontSize()
        }
    }

    fun animateToTarget() {

        if(size != null) {
            animateSizeToTarget()
        }

        if(shape != null) {
            animateShapeToTarget()
        }

        if(alpha != null) {
            animateAlphaToTarget()
        }

        if(offset != null) {
            animateOffsetToTarget()
        }

        if(fontSize != null) {
            animateFontSizeToTarget()
        }
    }

    fun animateToInitial() {

        if(size != null) {
            animateSizeToInitial()
        }

        if(shape != null) {
            animateShapeToInitial()
        }

        if(alpha != null) {
            animateAlphaToInitial()
        }

        if(offset != null) {
            animateOffsetToInitial()
        }

        if(fontSize != null) {
            animateFontSizeToInitial()
        }
    }

    private fun animateSize() {
        when(sizeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL  -> {
                animateSizeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                animateSizeToInitial()
            }
        }
    }
    private fun animateSizeToTarget() {
        setSizeAnim(AnimationState.INITIAL_TO_TARGET)
        size = targetSize
        sizeAnimationSpec = toTargetSizeAnimationSpec
    }
    private fun animateSizeToInitial() {
        setSizeAnim(AnimationState.TARGET_TO_INITIAL)
        size = initialSize
        sizeAnimationSpec = toInitialSizeAnimationSpec
    }
    internal val animatedSize: DpSize
        @Composable
        get() {
            size?.let { size ->
                sizeAnimationSpec?.let { spec ->

                    val animatedFloatSize = animateSizeAsState(
                        targetValue = Size(
                            width = if(size.width == Dp.Infinity) screenWidth.value else size.width.value,
                            height = if(size.height == Dp.Infinity) screenHeight.value else size.height.value
                        ),
                        animationSpec = spec,
                        finishedListener = {
                            when(sizeAnimState) {
                                AnimationState.INITIAL_TO_TARGET -> {
                                    setSizeAnim(AnimationState.TARGET)
                                }

                                AnimationState.TARGET_TO_INITIAL -> {
                                    setSizeAnim(AnimationState.INITIAL)
                                }
                                else -> {}
                            }
                        }
                    )
                    return DpSize(
                        Dp(animatedFloatSize.value.width),
                        Dp(animatedFloatSize.value.height)
                    )
                }
            }
            return DpSize(Dp.Unspecified, Dp.Unspecified)
        }


    private fun animateShape() {
        when(shapeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL-> {
                animateShapeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                animateShapeToInitial()
            }
        }
    }
    private fun animateShapeToTarget() {
        setShapeAnim(AnimationState.INITIAL_TO_TARGET)
        shape = targetShape
        shapeAnimationSpec = toTargetShapeAnimationSpec
    }
    private fun animateShapeToInitial() {
        setShapeAnim(AnimationState.TARGET_TO_INITIAL)
        shape = initialShape
        shapeAnimationSpec = toInitialShapeAnimationSpec
    }
    internal val animatedShape: RoundedCornerShape
        @Composable
        get() {
            shape?.let { shape ->

                shapeAnimationSpec?.let { spec ->

                    val targetShape: RoundedCornerShape = if(shape == CircleShape) {
                        if(size != null) {
                            RoundedCornerShape(
                                maxOf(
                                    size!!.width,
                                    size!!.height
                                )
                            )
                        }else {
                            throw IllegalArgumentException(
                                "Please specify size in state for use shape animation"
                            )
                        }
                    } else {
                        shape as RoundedCornerShape
                    }

                    targetSize?.let { size ->

                        val topStartCornerSize = targetShape.topStart.toPx(
                            shapeSize = Size(
                                width = size.width.value,
                                height = size.height.value
                            ),
                            density = Density(LocalContext.current)
                        )

                        val topEndCornerSize = targetShape.topEnd.toPx(
                            shapeSize = Size(
                                width = size.width.value,
                                height = size.height.value
                            ),
                            density = Density(LocalContext.current)
                        )
                        val bottomStartCornerSize = targetShape.bottomStart.toPx(
                            shapeSize = Size(
                                width = size.width.value,
                                height = size.height.value
                            ),
                            density = Density(LocalContext.current)
                        )
                        val bottomEndCornerSize = targetShape.bottomEnd.toPx(
                            shapeSize = Size(
                                width = size.width.value,
                                height = size.height.value
                            ),
                            density = Density(LocalContext.current)
                        )

                        val animatedTopStartCornerSize = animateFloatAsState(
                            targetValue = topStartCornerSize,
                            animationSpec = spec
                        )
                        val animatedTopEndCornerSize = animateFloatAsState(
                            targetValue = topEndCornerSize,
                            animationSpec = spec
                        )
                        val animatedBottomStartCornerSize = animateFloatAsState(
                            targetValue = bottomStartCornerSize,
                            animationSpec = spec
                        )
                        val animatedBottomEndCornerSize = animateFloatAsState(
                            targetValue = bottomEndCornerSize,
                            animationSpec = spec,
                            finishedListener = {
                                when(shapeAnimState) {
                                    AnimationState.INITIAL_TO_TARGET -> {
                                        setShapeAnim(AnimationState.TARGET)
                                    }
                                    AnimationState.TARGET_TO_INITIAL -> {
                                        setShapeAnim(AnimationState.INITIAL)
                                    }
                                    else -> {}
                                }
                            }
                        )

                        return RoundedCornerShape(
                            topStart = animatedTopStartCornerSize.value,
                            topEnd = animatedTopEndCornerSize.value,
                            bottomStart =  animatedBottomStartCornerSize.value,
                            bottomEnd = animatedBottomEndCornerSize.value,
                        )
                    }
                }
            }
            return RoundedCornerShape(0.dp)
        }

    private fun animateAlpha() {
        when(alphaAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL  -> {
                animateAlphaToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                animateAlphaToInitial()
            }
        }
    }
    private fun animateAlphaToTarget() {
        setAlphaAnim(AnimationState.INITIAL_TO_TARGET)
        alpha = targetAlpha
        alphaAnimationSpec = toTargetAlphaAnimationSpec
    }
    private fun animateAlphaToInitial() {
        setAlphaAnim(AnimationState.TARGET_TO_INITIAL)
        alpha = initialAlpha
        alphaAnimationSpec = toInitialAlphaAnimationSpec
    }
    internal val animatedAlpha: Float
        @Composable
        get() {
            alpha?.let { alpha ->
                alphaAnimationSpec?.let { spec ->
                    return animateFloatAsState(
                        targetValue = alpha,
                        animationSpec = spec,
                        finishedListener = {
                            when(alphaAnimState) {
                                AnimationState.INITIAL_TO_TARGET -> {
                                    setAlphaAnim(AnimationState.TARGET)
                                }

                                AnimationState.TARGET_TO_INITIAL -> {
                                    setAlphaAnim(AnimationState.INITIAL)
                                }
                                else -> {}
                            }
                        }
                    ).value
                }
            }
            return 1f
        }

    private fun animateOffset() {
        when(offsetAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL  -> {
                animateOffsetToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                animateOffsetToInitial()
            }
        }
    }
    private fun animateOffsetToTarget() {
        setOffsetAnim(AnimationState.INITIAL_TO_TARGET)
        offset = targetOffset
        offsetAnimationSpec = toTargetOffsetAnimationSpec
    }
    private fun animateOffsetToInitial() {
        setOffsetAnim(AnimationState.TARGET_TO_INITIAL)
        offset = initialOffset
        offsetAnimationSpec =  toInitialOffsetAnimationSpec
    }
    internal val animatedOffset: DpOffset
        @Composable
        get() {
            offset?.let { offset ->

                offsetAnimationSpec?.let { spec ->

                    val reminderOfWidth: Dp
                    val reminderOfHeight: Dp

                    initialOffset?.let { initialOffset ->

                        targetOffset?.let { targetOffset ->

                            if(size == null) {
                                if(initialOffset.x == Dp.Infinity
                                    || initialOffset.x == - Dp.Infinity
                                    || initialOffset.y == Dp.Infinity
                                    || initialOffset.y == - Dp.Infinity
                                    || targetOffset.x == Dp.Infinity
                                    || targetOffset.x == - Dp.Infinity
                                    || targetOffset.y == Dp.Infinity
                                    || targetOffset.y == - Dp.Infinity
                                ){
                                    throw IllegalArgumentException(
                                        "Please specify size in state for use Dp.Infinity in offset animation"
                                    )
                                }else {
                                    reminderOfWidth = 0.dp
                                    reminderOfHeight = 0.dp
                                }
                            }else {
                                reminderOfWidth = if(size!!.width == Dp.Infinity) {
                                    0.dp
                                } else {
                                    ((screenWidth - (size!!.width)) / 2)
                                }
                                reminderOfHeight = if(size!!.height == Dp.Infinity) {
                                    0.dp
                                } else {
                                    ((screenHeight - (size!!.height)) / 2)
                                }
                            }

                            val animatedOffset = animateSizeAsState(
                                targetValue = Size(
                                    width = when(offset.x) {
                                        Dp.Infinity -> reminderOfWidth.value
                                        - Dp.Infinity -> - reminderOfWidth.value
                                        else -> offset.x.value
                                    },
                                    height = when(offset.y) {
                                        Dp.Infinity -> reminderOfHeight.value
                                        - Dp.Infinity -> - reminderOfHeight.value
                                        else -> offset.y.value
                                    }
                                ),
                                animationSpec = spec,
                                finishedListener = {
                                    when(offsetAnimState) {
                                        AnimationState.INITIAL_TO_TARGET -> {
                                            setOffsetAnim(AnimationState.TARGET)
                                        }

                                        AnimationState.TARGET_TO_INITIAL -> {
                                            setOffsetAnim(AnimationState.INITIAL)
                                        }
                                        else -> {}
                                    }
                                }
                            )
                            return DpOffset(
                                Dp(animatedOffset.value.width),
                                Dp(animatedOffset.value.height)
                            )
                        }
                    }
                }
            }
            return DpOffset(x = 0.dp, y = 0.dp)
        }

    private fun animateFontSize() {
        when(fontSizeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL -> {
                animateFontSizeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                animateFontSizeToInitial()
            }
        }
    }
    private fun animateFontSizeToTarget() {
        setFontSizeAnim(AnimationState.INITIAL_TO_TARGET)
        fontSize = targetFontSize
        fontSizeAnimationSpec = toTargetFontSizeAnimationSpec
    }
    private fun animateFontSizeToInitial() {
        setFontSizeAnim(AnimationState.TARGET_TO_INITIAL)
        fontSize = initialFontSize
        fontSizeAnimationSpec =  toInitialFontSizeAnimationSpec
    }
    internal val animatedFontSize: TextUnit
        @Composable
        get() {
            fontSize?.let { fontSize ->
                fontSizeAnimationSpec?.let { spec ->
                    return animateFloatAsState(
                        targetValue = fontSize.value,
                        animationSpec = spec,
                        finishedListener = {
                            when(fontSizeAnimState) {
                                AnimationState.INITIAL_TO_TARGET -> {
                                    setFontSizeAnim(AnimationState.TARGET)
                                }
                                AnimationState.TARGET_TO_INITIAL -> {
                                    setFontSizeAnim(AnimationState.INITIAL)
                                }
                                else -> {}
                            }
                        }
                    ).value.sp
                }
            }
            return TextUnit.Unspecified
        }

    private fun calculateSharedAnimationState() {
        val animationStates = listOf(
            sizeAnimState,
            shapeAnimState,
            alphaAnimState,
            offsetAnimState,
            fontSizeAnimState
        )
        if(animationStates.all { it == AnimationState.INITIAL }) {
            onAnimation(AnimationState.INITIAL)
            return
        }
        if(animationStates.all { it == AnimationState.INITIAL_TO_TARGET }) {
            onAnimation(AnimationState.INITIAL_TO_TARGET)
            return
        }
        if(animationStates.all { it == AnimationState.TARGET }) {
            onAnimation(AnimationState.TARGET)
            return
        }
        if(animationStates.all { it == AnimationState.TARGET_TO_INITIAL }) {
            onAnimation(AnimationState.TARGET_TO_INITIAL)
            return
        }
    }
}

enum class AnimationState {
    INITIAL, INITIAL_TO_TARGET, TARGET_TO_INITIAL, TARGET
}

enum class AnimatableStateTag {
    BOX, TEXT, CARD, ICON,
}