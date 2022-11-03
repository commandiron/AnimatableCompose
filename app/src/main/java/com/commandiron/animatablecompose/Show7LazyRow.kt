package com.commandiron.animatablecompose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.animatable_compose.AnimatableCard
import com.commandiron.animatable_compose.AnimatableLazyRow
import com.commandiron.animatable_compose.state.AnimatableState
import com.commandiron.animatable_compose.state.AnimationState
import com.commandiron.animatable_compose.state.rememberAnimatableCardState
import com.commandiron.animatable_compose.state.rememberSharedAnimatableState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Show7LazyRow() {
    var selectedIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val cards by remember  {
        mutableStateOf(listOf("0","1","2","3","4","5","6","7","8","9"))
    }
    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 70.dp, height = 70.dp),
        targetSize = DpSize(width = Dp.Infinity, height = Dp.Infinity),
        initialShape = CircleShape,
        targetShape = RoundedCornerShape(16.dp)
    )
    val cardStates = mutableListOf<AnimatableState>()
    cards.indices.forEach { index ->
        cardStates.add(
            animatableCardState.copy(
                index = index,
                onAnimation = {
                    when(it) {
                        AnimationState.INITIAL -> {}
                        AnimationState.INITIAL_TO_TARGET -> {
                            scope.launch {
                                delay(300)
                                lazyListState.animateScrollToItem(selectedIndex)
                            }
                        }
                        AnimationState.TARGET_TO_INITIAL -> {}
                        AnimationState.TARGET -> {}
                    }
                }
            )
        )
    }

    val sharedAnimatableState = rememberSharedAnimatableState(cardStates)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatableLazyRow(
            modifier = Modifier.border(1.dp, Color.Red),
            lazyListState = lazyListState,
            state = sharedAnimatableState
        ) {
            items(cards.size) { index ->
                AnimatableCard(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp),
                    onClick = {
                        selectedIndex = index
                        cardStates[index].animate()
                    },
                    state = sharedAnimatableState,
                    stateIndex = index
                ) {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text(text = cards[index])
                    }
                }
            }
            item {
                Spacer(Modifier.width(70.dp))
            }
        }
    }
}