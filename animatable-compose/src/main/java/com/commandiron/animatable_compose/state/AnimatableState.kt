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
fun rememberAnimatableSharedState(
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    sizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeTransform: (AnimationState) -> Unit = {},
    initialShape: Shape? = null,
    targetShape: Shape? = null,
    shapeAnimationSpec: AnimationSpec<Float> = tween(500),
    onShapeTransform: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    offsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetTransform: (AnimationState) -> Unit = {},
    initialFontSize: TextUnit? = null,
    targetFontSize: TextUnit? = null,
    fontSizeAnimationSpec: AnimationSpec<Float> = tween(500),
    onFontSizeTransform: (AnimationState) -> Unit = {},
    onTransform: (AnimationState) -> Unit = {}
): AnimatableState {
    return remember {
        AnimatableState(
            initialSize = initialSize,
            targetSize = targetSize,
            sizeAnimationSpec = sizeAnimationSpec,
            onSizeTransform = onSizeTransform,
            initialShape = initialShape,
            targetShape = targetShape,
            shapeAnimationSpec = shapeAnimationSpec,
            onShapeTransform = onShapeTransform,
            initialOffset = initialOffset,
            targetOffset = targetOffset,
            offsetAnimationSpec = offsetAnimationSpec,
            onOffsetTransform = onOffsetTransform,
            initialFontSize = initialFontSize,
            targetFontSize = targetFontSize,
            fontSizeAnimationSpec = fontSizeAnimationSpec,
            onFontSizeTransform = onFontSizeTransform,
            onTransform = onTransform
        )
    }
}

@Composable
fun rememberAnimatableBoxState(
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    sizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeTransform: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    offsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetTransform: (AnimationState) -> Unit = {},
): AnimatableState {
    return AnimatableState(
        initialSize = initialSize,
        targetSize = targetSize,
        sizeAnimationSpec = sizeAnimationSpec,
        onSizeTransform = onSizeTransform,
        initialOffset = initialOffset,
        targetOffset = targetOffset,
        offsetAnimationSpec = offsetAnimationSpec,
        onOffsetTransform = onOffsetTransform
    )
}

@Composable
fun rememberAnimatableCardState(
    initialSize: DpSize? = null,
    targetSize: DpSize? = null,
    sizeAnimationSpec: AnimationSpec<Size> = tween(500),
    onSizeTransform: (AnimationState) -> Unit = {},
    initialShape: Shape? = null,
    targetShape: Shape? = null,
    shapeAnimationSpec: AnimationSpec<Float> = tween(500),
    onShapeTransform: (AnimationState) -> Unit = {},
    initialOffset: DpOffset? = null,
    targetOffset: DpOffset? = null,
    offsetAnimationSpec: AnimationSpec<Size> = tween(500),
    onOffsetTransform: (AnimationState) -> Unit = {},
): AnimatableState {
    return AnimatableState(
        initialSize = initialSize,
        targetSize = targetSize,
        sizeAnimationSpec = sizeAnimationSpec,
        onSizeTransform = onSizeTransform,
        initialShape = initialShape,
        targetShape = targetShape,
        shapeAnimationSpec = shapeAnimationSpec,
        onShapeTransform = onShapeTransform,
        initialOffset = initialOffset,
        targetOffset = targetOffset,
        offsetAnimationSpec = offsetAnimationSpec,
        onOffsetTransform = onOffsetTransform,
    )
}

@Composable
fun rememberAnimatableTextState(
    initialFontSize: TextUnit,
    targetFontSize: TextUnit,
    fontSizeAnimationSpec: AnimationSpec<Float> = tween(500),
    onFontSizeTransform: (AnimationState) -> Unit = {},
): AnimatableState {
    return remember {
        AnimatableState(
            initialFontSize = initialFontSize,
            targetFontSize = targetFontSize,
            fontSizeAnimationSpec = fontSizeAnimationSpec,
            onFontSizeTransform = onFontSizeTransform
        )
    }
}

class AnimatableState(
    private val initialSize: DpSize? = null,
    private val targetSize: DpSize? = null,
    private val sizeAnimationSpec: AnimationSpec<Size>? = null,
    private val onSizeTransform: (AnimationState) -> Unit = {},

    private val initialShape: Shape? = null,
    private val targetShape: Shape? = null,
    private val shapeAnimationSpec: AnimationSpec<Float>? = null,
    private val onShapeTransform: (AnimationState) -> Unit = {},

    private val initialOffset: DpOffset? = null,
    private val targetOffset: DpOffset? = null,
    private val offsetAnimationSpec: AnimationSpec<Size>? = null,
    private val onOffsetTransform: (AnimationState) -> Unit = {},

    private val initialFontSize: TextUnit? = null,
    private val targetFontSize: TextUnit? = null,
    private val fontSizeAnimationSpec: AnimationSpec<Float>? = null,
    private val onFontSizeTransform: (AnimationState) -> Unit = {},

    private val onTransform: (AnimationState) -> Unit = {}
) {

    private var size by mutableStateOf(initialSize)
    private var sizeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setSizeAnim(animationState: AnimationState) {
        sizeAnimState = animationState
        onSizeTransform(animationState)
        calculateSharedAnimationState()
    }

    private var shape by mutableStateOf(initialShape)
    private var shapeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setShapeAnim(animationState: AnimationState) {
        shapeAnimState = animationState
        onShapeTransform(animationState)
        calculateSharedAnimationState()
    }

    private var offset by mutableStateOf(initialOffset)
    private var offsetAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setOffsetAnim(animationState: AnimationState) {
        offsetAnimState = animationState
        onOffsetTransform(animationState)
        calculateSharedAnimationState()
    }

    private var fontSize by  mutableStateOf(initialFontSize)
    private var fontSizeAnimState by mutableStateOf(AnimationState.INITIAL)
    private fun setFontSizeAnim(animationState: AnimationState) {
        fontSizeAnimState = animationState
        onFontSizeTransform(animationState)
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

    suspend fun transform() {

        if(size != null) {
            transformSize()
        }

        if(offset != null) {
            transformOffset()
        }

        if(shape != null) {
            transformShape()
        }

        if(fontSize != null) {
            transformFontSize()
        }
    }

    fun transformToTarget() {
        if(size != null) {
            transformSizeToTarget()
        }

        if(offset != null) {
            transformOffsetToTarget()
        }

        if(shape != null) {
            transformShapeToTarget()
        }

        if(fontSize != null) {
            transformFontSizeToTarget()
        }
    }

    fun transformToInitial() {
        if(size != null) {
            transformSizeToInitial()
        }

        if(offset != null) {
            transformOffsetToInitial()
        }

        if(shape != null) {
            transformShapeToInitial()
        }

        if(fontSize != null) {
            transformFontSizeToInitial()
        }
    }

    private fun transformSize() {
        when(sizeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL  -> {
                transformSizeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                transformSizeToInitial()
            }
        }
    }
    private fun transformSizeToTarget() {
        setSizeAnim(AnimationState.INITIAL_TO_TARGET)
        size = targetSize
    }
    private fun transformSizeToInitial() {
        setSizeAnim(AnimationState.TARGET_TO_INITIAL)
        size = initialSize
    }
    val animatedSize: DpSize
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


    private fun transformShape() {
        when(shapeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL-> {
                transformShapeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                transformShapeToInitial()
            }
        }
    }
    private fun transformShapeToTarget() {
        setShapeAnim(AnimationState.INITIAL_TO_TARGET)
        shape = targetShape
    }
    private fun transformShapeToInitial() {
        setShapeAnim(AnimationState.TARGET_TO_INITIAL)
        shape = initialShape
    }
    val animatedShape: RoundedCornerShape
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

    private fun transformOffset() {
        when(offsetAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL  -> {
                transformOffsetToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                transformOffsetToInitial()
            }
        }
    }
    private fun transformOffsetToTarget() {
        setOffsetAnim(AnimationState.INITIAL_TO_TARGET)
        offset = targetOffset
    }
    private fun transformOffsetToInitial() {
        setOffsetAnim(AnimationState.TARGET_TO_INITIAL)
        offset = initialOffset
    }
    val animatedOffset: DpOffset
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
                                    || initialOffset.x == - Dp.Infinity
                                    || targetOffset.x == Dp.Infinity
                                    || targetOffset.x == - Dp.Infinity
                                    || targetOffset.y == Dp.Infinity
                                    || targetOffset.x == - Dp.Infinity
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

    private fun transformFontSize() {
        when(fontSizeAnimState) {
            AnimationState.INITIAL, AnimationState.TARGET_TO_INITIAL -> {
                transformFontSizeToTarget()
            }
            AnimationState.TARGET, AnimationState.INITIAL_TO_TARGET -> {
                transformFontSizeToInitial()
            }
        }
    }
    private fun transformFontSizeToTarget() {
        setFontSizeAnim(AnimationState.INITIAL_TO_TARGET)
        fontSize = targetFontSize
    }
    private fun transformFontSizeToInitial() {
        setFontSizeAnim(AnimationState.TARGET_TO_INITIAL)
        fontSize = initialFontSize
    }
    val animatedFontSize: TextUnit
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
            offsetAnimState,
            shapeAnimState,
            fontSizeAnimState
        )
        if(animationStates.all { it == AnimationState.INITIAL }) {
            onTransform(AnimationState.INITIAL)
            return
        }
        if(animationStates.all { it == AnimationState.INITIAL_TO_TARGET }) {
            onTransform(AnimationState.INITIAL_TO_TARGET)
            return
        }
        if(animationStates.all { it == AnimationState.TARGET }) {
            onTransform(AnimationState.TARGET)
            return
        }
        if(animationStates.all { it == AnimationState.TARGET_TO_INITIAL }) {
            onTransform(AnimationState.TARGET_TO_INITIAL)
            return
        }
    }
}

enum class AnimationState {
    INITIAL, INITIAL_TO_TARGET, TARGET_TO_INITIAL, TARGET
}