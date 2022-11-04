package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.commandiron.animatable_compose.*
import com.commandiron.animatable_compose.state.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun Show8InfoCard() {

    val lazyListState = rememberLazyListState()
    val snapperFlingBehavior = rememberSnapperFlingBehavior(
        lazyListState = lazyListState,
        snapOffsetForItem = SnapOffsets.Start
    )
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    val animatableCardState = rememberAnimatableCardState(
        initialSize = DpSize(width = 340.dp, height = 180.dp),
        targetSize = DpSize(width = Dp.Infinity, height = 340.dp),
        initialShape = RoundedCornerShape(32.dp),
        targetShape = RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp),
        toTargetShapeAnimationSpec = tween(750),
        initialPadding = PaddingValues(horizontal = 8.dp),
        targetPadding = PaddingValues(0.dp),
        onAnimation = {
            when(it) {
                AnimationState.INITIAL -> {}
                AnimationState.INITIAL_TO_TARGET -> {
                    scope.launch {
                        delay(500)
                        lazyListState.animateScrollToItem(selectedIndex)
                    }
                }
                AnimationState.TARGET -> {}
                AnimationState.TARGET_TO_INITIAL -> {}
            }
        }
    )
    val animatableBoxState = rememberAnimatableBoxState(
        initialAlignment = Alignment.Center,
        targetAlignment = Alignment.TopCenter
    )
    val animatableTextState = rememberAnimatableTextState(
        initialFontSize = 0.sp,
        targetFontSize = 12.sp,
        initialOffset = DpOffset(x = 0.dp, y = 300.dp),
        targetOffset = DpOffset(x = 0.dp, y = 0.dp),
        toTargetAnimationSpec = tween(250)
    )
    val animatableSpacerState = rememberAnimatableSpacerState(
        initialSize = DpSize(width = 0.dp, height = 0.dp),
        targetSize = DpSize(width = 0.dp, height = 16.dp)
    )

    val infoCards by remember { mutableStateOf(InfoCard.infoCards) }

    val cardStates = mutableListOf<AnimatableState>()
    val boxStates = mutableListOf<AnimatableState>()
    val textStates = mutableListOf<AnimatableState>()
    val spacerStates = mutableListOf<AnimatableState>()

    infoCards.indices.forEach { index ->
        cardStates.add(
            animatableCardState.copy(
                index = index
            )
        )
        boxStates.add(
            animatableBoxState.copy(
                index = index
            )
        )
        textStates.add(
            animatableTextState.copy(
                index = index
            )
        )
        if(index == 0) {
            spacerStates.add(
                animatableSpacerState.copy(
                    index = index,
                    initialSize = DpSize(width = 0.dp, height = 300.dp),
                    targetSize = DpSize(width = 0.dp, height = 0.dp)
                )
            )
        }
        spacerStates.add(
            animatableSpacerState.copy(
                index = index + 1,
            )
        )
    }

    val sharedAnimatableState = rememberSharedAnimatableState(
        animatableStates = cardStates + boxStates + textStates + spacerStates
    )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatableSpacer(
            state = sharedAnimatableState
        )
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            state = lazyListState,
            flingBehavior = snapperFlingBehavior
        ) {
            items(infoCards.size) { index ->
                AnimatableCard(
                    onClick = {
                        selectedIndex = index
                        sharedAnimatableState.animate()
                    },
                    state = sharedAnimatableState,
                    stateIndex = index,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE9E7FE)
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AnimatableBox(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(16.dp),
                            stateIndex = index,
                            state = sharedAnimatableState
                        ) {
                            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                                item {
                                    Text(
                                        text = infoCards[index].title,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.align(Alignment.CenterStart),
                                        text = "MGS 1",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    AnimatableSpacer(
                                        stateIndex = index + 1,
                                        state = sharedAnimatableState
                                    )
                                    AnimatableText(
                                        text = infoCards[index].info,
                                        stateIndex = index,
                                        state = sharedAnimatableState,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        AsyncImage(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(32.dp)),
                            model = infoCards[index].imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}


data class InfoCard(
    val imageUrl: String,
    val title: String,
    val info: String
){
    companion object {
        val infoCards = listOf(
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/f/fb/MGS1SnakePP.png/revision/latest?cb=20180819204819",
                "Solid Snake",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/5/55/Liquid1I2.png/revision/latest?cb=20130211020413",
                "Liquid Snake",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/c/c2/MGS1OcelotPP.png/revision/latest?cb=20131221073643",
                "Ocelot",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/3/36/Mantis1I1.png/revision/latest?cb=20130211020311",
                "Mantis",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/6/62/MGSOtaconPP.png/revision/latest?cb=20131222063336",
                "Otocon",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/a/a8/MGSMerylPP.png/revision/latest?cb=20131222063857",
                "Meryl",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/c/c8/SolidusHD.jpg/revision/latest/top-crop/width/360/height/360?cb=20140930091309",
                "Solidus",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/5/5e/GrayFox1I3.png/revision/latest?cb=20130211015925",
                "Gray Fox",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            ),
            InfoCard(
                "https://static.wikia.nocookie.net/metalgear/images/0/0e/Raven1I2.png/revision/latest?cb=20130211020140",
                "Raven",
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio."
            )
        )
    }
}