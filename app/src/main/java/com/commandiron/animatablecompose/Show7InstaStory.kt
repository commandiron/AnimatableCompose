package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimationState
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import com.commandiron.animatable_compose.state.rememberSharedAnimatableState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Show7InstaStory() {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    val stories by remember { mutableStateOf(Story.stories) }

    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 70.dp, height = 70.dp),
        targetSize = DpSize(width = Dp.Infinity, height = Dp.Infinity),
        initialShape = CircleShape,
        targetShape = RoundedCornerShape(0.dp),
        initialPadding = PaddingValues(4.dp, 8.dp),
        targetPadding = PaddingValues(0.dp),
        initialBorder = BorderStroke(2.dp, Brush.verticalGradient(listOf(Color.Red, Color.Yellow))),
        targetBorder = BorderStroke(0.dp, Color.Unspecified)
    )

    val cardStates = mutableListOf<AnimatableState>()

    stories.indices.forEach { index ->
        cardStates.add(
            animatableCardState.copy(
                index = index,
                onAnimation = {
                    when(it) {
                        AnimationState.INITIAL -> {}
                        AnimationState.INITIAL_TO_TARGET -> {
                            scope.launch {
                                delay(150)
                                lazyListState.animateScrollToItem(selectedIndex)
                            }
                        }
                        AnimationState.TARGET -> {}
                        AnimationState.TARGET_TO_INITIAL -> {}
                    }
                },
                toTargetAnimationSpec = tween(250)
            )
        )
    }

    val sharedAnimatableState = rememberSharedAnimatableState(cardStates)

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyRow(
            state = lazyListState
        ) {
            items(stories.size) { index ->
                AnimatableCard(
                    modifier = Modifier
                        .size(100.dp),
                    onClick = {
                        selectedIndex = index
                        cardStates[index].animate()
                    },
                    state = sharedAnimatableState,
                    stateIndex = index
                ) {
                    AsyncImage(
                        model = stories[index].url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

data class Story(
    val url: String
) {
    companion object {
        val stories = listOf(
            Story("https://static.wikia.nocookie.net/metalgear/images/f/fb/MGS1SnakePP.png/revision/latest?cb=20180819204819"),
            Story("https://static.wikia.nocookie.net/metalgear/images/5/55/Liquid1I2.png/revision/latest?cb=20130211020413"),
            Story("https://static.wikia.nocookie.net/metalgear/images/c/c2/MGS1OcelotPP.png/revision/latest?cb=20131221073643"),
            Story("https://static.wikia.nocookie.net/metalgear/images/3/36/Mantis1I1.png/revision/latest?cb=20130211020311"),
            Story("https://static.wikia.nocookie.net/metalgear/images/6/62/MGSOtaconPP.png/revision/latest?cb=20131222063336"),
            Story("https://static.wikia.nocookie.net/metalgear/images/a/a8/MGSMerylPP.png/revision/latest?cb=20131222063857"),
            Story("https://static.wikia.nocookie.net/metalgear/images/c/c8/SolidusHD.jpg/revision/latest/top-crop/width/360/height/360?cb=20140930091309"),
            Story("https://static.wikia.nocookie.net/metalgear/images/5/5e/GrayFox1I3.png/revision/latest?cb=20130211015925"),
            Story("https://static.wikia.nocookie.net/metalgear/images/0/0e/Raven1I2.png/revision/latest?cb=20130211020140")
        )
    }
}