# AnimatableCompose [![](https://jitpack.io/v/commandiron/AnimatableCompose.svg)](https://jitpack.io/#commandiron/AnimatableCompose)

Add Animatable Material Components in Android Jetpack Compose. 

Create jetpack compose animation painless.

What you can create from Material 3 components right now;
* Text Animation
* Box Animation
* Card Animation
* Icon Animation
* and combinations

## How it looks

<img src="https://user-images.githubusercontent.com/50905347/197984728-7bfe5536-b78e-41e1-91cb-5bc167e51850.gif" width="250" height="530">&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/50905347/198032696-f78f2b66-964c-494d-9614-14107ecde244.gif" width="250" height="530">

### Expandable Phone Number Animation

<details closed>
<summary>States</summary>
<br>

        
```kotlin
//Create components state
val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(80.dp, 80.dp),
    targetSize = DpSize(Dp.Infinity, 120.dp),
    toTargetSizeAnimationSpec = tween(500, 500), //  specify delay(500) for target
    initialShape = RoundedCornerShape(32.dp),
    targetShape = RoundedCornerShape(0.dp),
    toTargetShapeAnimationSpec = tween(500, 500),
    initialOffset = DpOffset(0.dp, 0.dp),
    targetOffset = DpOffset(0.dp, - Dp.Infinity),
    toInitialOffsetAnimationSpec = tween(500, 500),
)
val animatableIconState = rememberAnimatableIconState(
    initialSize = DpSize(40.dp, 40.dp),
    targetSize = DpSize(80.dp, 80.dp),
    toTargetSizeAnimationSpec = tween(500,500),
    initialOffset = DpOffset(0.dp, 0.dp),
    targetOffset = DpOffset((-50).dp, 0.dp),
    toTargetOffsetAnimationSpec = tween(500, 500)
)
val animatableTextState = rememberAnimatableTextState(
    initialFontSize = 0.sp,
    targetFontSize = 26.sp,
    toTargetFontSizeAnimationSpec = tween(500, 500),
    initialOffset = DpOffset(0.dp, 0.dp),
    targetOffset = DpOffset((-25).dp, 0.dp),
    toTargetOffsetAnimationSpec = tween(500, 500)
)
        
// Create shared state
val sharedAnimatableState = rememberSharedAnimatableState(
    listOf(
        animatableCardState,
        animatableIconState, // default index = 0
        animatableIconState.copy( // create state with copy func. for same params
            index = 1, // specify index for same components
            initialSize = DpSize(0.dp, 0.dp),
            targetSize = DpSize(36.dp, 36.dp),
            targetOffset = DpOffset(40.dp, 0.dp),
        ),
        animatableTextState, // default index = 0
        animatableTextState.copy(
            index = 1, // specify index for same components
            targetFontSize = 12.sp
        )
    )
)
```
</details>
<details closed>
<summary>Components</summary>
<br>

        
```kotlin
AnimatableCard(
    onClick = {
        sharedAnimatableState.animate()
    },
    state = sharedAnimatableState // pass shared state
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatableIcon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            state = sharedAnimatableState // pass shared state
        )
        Column {
            AnimatableText(
                text = "Emir Demirli",
                state = sharedAnimatableState // pass shared state
            )
            AnimatableText(
                text = "+90 0535 508 55 52",
                state = sharedAnimatableState, // pass shared state
                stateIndex = 1 // specify index for same components
            )
        }
        AnimatableIcon(
            imageVector = Icons.Default.Phone,
            contentDescription = null,
            state = sharedAnimatableState, // pass shared state
            stateIndex = 1 // specify index for same components
        )
    }
}
```
</details>

### Card Dealer Animation

<details closed>
<summary>States</summary>
<br>

        
```kotlin
val cards by remember  { 
    mutableStateOf(listOf("A","K","Q","J","10","9","8","7","6","5","4","3","2"))
}
var deck by remember {
    mutableStateOf(cards + cards + cards + cards)
}

val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(64.dp, 64.dp),
    targetSize = DpSize(64.dp, 64.dp),
    initialOffset = DpOffset(0.dp, 120.dp),
    targetOffset = DpOffset(-Dp.Infinity, -Dp.Infinity)
)
val animatableTextState = rememberAnimatableTextState(
    initialFontSize = 0.sp,
    targetFontSize = 24.sp
)

val cardStates = mutableListOf<AnimatableState>()
val textStates = mutableListOf<AnimatableState>()

deck.indices.forEach {
    cardStates.add(
        animatableCardState.copy(
            index = it,
            toTargetOffsetAnimationSpec = tween(400, (it * 400)),
            targetOffset = DpOffset(if(it % 2 == 0) (-100).dp else 100.dp, (-150).dp)
        )
    )
    textStates.add(
        animatableTextState.copy(
            index = it,
            toTargetFontSizeAnimationSpec = tween(400, (it * 400))
        )
    )

}

val sharedAnimatableState = rememberSharedAnimatableState(cardStates + textStates)
```
</details>
<details closed>
<summary>Components</summary>
<br>

        
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .clickable {
            deck = deck.shuffled()
            sharedAnimatableState.animate()
        },
    contentAlignment = Alignment.Center
) {
    deck.indices.forEach {
        AnimatableCard(
            onClick = {},
            state = sharedAnimatableState,
            stateIndex = it,
            fixedShape = RoundedCornerShape(16.dp)
        ) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                AnimatableText(
                    text = deck[it],
                    state = sharedAnimatableState,
                    stateIndex = it
                )
            }
        }
    }
}
```
</details>

### Insta Story

<details closed>
<summary>States</summary>
<br>

        
```kotlin
val cards by remember  { 
    mutableStateOf(listOf("A","K","Q","J","10","9","8","7","6","5","4","3","2"))
}
var deck by remember {
    mutableStateOf(cards + cards + cards + cards)
}

val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(64.dp, 64.dp),
    targetSize = DpSize(64.dp, 64.dp),
    initialOffset = DpOffset(0.dp, 120.dp),
    targetOffset = DpOffset(-Dp.Infinity, -Dp.Infinity)
)
val animatableTextState = rememberAnimatableTextState(
    initialFontSize = 0.sp,
    targetFontSize = 24.sp
)

val cardStates = mutableListOf<AnimatableState>()
val textStates = mutableListOf<AnimatableState>()

deck.indices.forEach {
    cardStates.add(
        animatableCardState.copy(
            index = it,
            toTargetOffsetAnimationSpec = tween(400, (it * 400)),
            targetOffset = DpOffset(if(it % 2 == 0) (-100).dp else 100.dp, (-150).dp)
        )
    )
    textStates.add(
        animatableTextState.copy(
            index = it,
            toTargetFontSizeAnimationSpec = tween(400, (it * 400))
        )
    )

}

val sharedAnimatableState = rememberSharedAnimatableState(cardStates + textStates)
```
</details>
<details closed>
<summary>Components</summary>
<br>

        
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .clickable {
            deck = deck.shuffled()
            sharedAnimatableState.animate()
        },
    contentAlignment = Alignment.Center
) {
    deck.indices.forEach {
        AnimatableCard(
            onClick = {},
            state = sharedAnimatableState,
            stateIndex = it,
            fixedShape = RoundedCornerShape(16.dp)
        ) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                AnimatableText(
                    text = deck[it],
                    state = sharedAnimatableState,
                    stateIndex = it
                )
            }
        }
    }
}
```
</details>
        
## How to use
        
You can learn to use it step by step, you need to use state and components together.

### AnimatableText

<img src="https://user-images.githubusercontent.com/50905347/197984582-1988a82a-db0a-4e8f-a1f7-f0a134b8e45a.gif" width="250" height="530">

<details closed>
<summary>State</summary>
<br>

        
```kotlin
// Simply create state and pass it to AnimatableText
val state = rememberAnimatableTextState(
    initialFontSize = 12.sp,
    targetFontSize = 60.sp
)
```
</details>
<details closed>
<summary>Component</summary>
<br>

        
```kotlin
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
```
</details>

### AnimatableBox

<img src="https://user-images.githubusercontent.com/50905347/199469778-3164046f-25d6-4199-863a-7a41f93fb608.gif" width="250" height="530">

<details closed>
<summary>State</summary>
<br>

        
```kotlin
// Simply create box state and pass it to AnimatableBox
val state = rememberAnimatableBoxState(
    initialSize = DpSize(60.dp, 60.dp), // set initial size
    targetSize = DpSize(Dp.Infinity, 120.dp), // set target size
    initialOffset = DpOffset(x = 0.dp, y = 0.dp), // set initial offset
    targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity) // set target offset
    // Dp.Infinity will take the maximum value according to the screen size, 
    // ps: Dp.Infinity for offset needs centered component and sizes, however you may not use it if you want
    initialAlignment = Alignment.Center,  // set initial alignment
    targetAlignment = Alignment.TopStart // set target alignment
)
```
</details>
<details closed>
<summary>Component</summary>
<br>

        
```kotlin
AnimatableBox(
    modifier = Modifier
        .border(1.dp, Color.Red)
        .clickable {
            state.animate()
        },
    state = state
) {
    Icon(
        modifier = Modifier.padding(8.dp),
        imageVector = Icons.Default.Add,
        contentDescription = null
    )
}
```
</details>

### AnimatableCard

<img src="https://user-images.githubusercontent.com/50905347/197984698-12536dc4-9a5b-40e1-9627-484738600b60.gif" width="250" height="530">

<details closed>
<summary>State</summary>
<br>

        
```kotlin
// Simply create card state and pass it to AnimatableCard
val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(width = 70.dp, height = 70.dp),
    targetSize = DpSize(width = 200.dp, height = 70.dp),
    initialShape = CircleShape,
    targetShape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 0.dp),
    initialOffset = DpOffset(x = 0.dp, y = 0.dp),
    targetOffset = DpOffset(x = - Dp.Infinity, y = - Dp.Infinity)
)
```
</details>
<details closed>
<summary>Component</summary>
<br>

        
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .clickable {
            animatableCardState.animateToInitial() // animate to initial
        },
    contentAlignment = Alignment.Center
) {
    AnimatableCard(
        modifier = Modifier.size(100.dp),
        onClick = {
            animatableCardState.animateToTarget() // animate to target
        },
        state = animatableCardState
    ) {}
}
```
</details>

### AnimatableCardWithText

<img src="https://user-images.githubusercontent.com/50905347/199411703-02d14430-853a-430a-a7a3-2d920eaa5fe3.gif" width="250" height="530">

<details closed>
<summary>States</summary>
<br>

        
```kotlin
// Simply create card state and text state
val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(width = 50.dp, height = 25.dp),
    targetSize = DpSize(width = 300.dp, height = 150.dp),
    initialShape = CircleShape,
    targetShape = RoundedCornerShape(16.dp)
)
val animatableTextState = rememberAnimatableTextState(
    initialFontSize = 4.sp,
    targetFontSize = 36.sp
)
// Merge the states you created into sharedState and pass it to AnimatableCard and AnimatableText
val sharedAnimatableState = rememberSharedAnimatableState(
    animatableStates = listOf(
        animatableCardState,
        animatableTextState
    ),
    toTargetAnimationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse) //specify shared animation spec
)
```
</details>
<details closed>
<summary>Components</summary>
<br>

        
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .clickable { sharedAnimatableState.animate() },
    contentAlignment = Alignment.Center
) {
    AnimatableCard(
        modifier = Modifier.size(100.dp),
        state = sharedAnimatableState // pass shared state
    ) {
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            AnimatableText(
                text = "Animatable",
                state = sharedAnimatableState // pass shared state
            )
        }
    }
}
```
</details>

## Setup
1. Open the file `settings.gradle` (it looks like that)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // add jitpack here 👇🏽
        maven { url 'https://jitpack.io' }
       ...
    }
} 
...
```
2. Sync the project
3. Add dependencies
```groovy
dependencies {
    implementation 'com.github.commandiron:AnimatableCompose:1.0.3'
}
```

## Todo ✔️
   * SharedAnimationSpec ✔️
