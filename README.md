# AnimatableCompose [![](https://jitpack.io/v/commandiron/AnimatableCompose.svg)](https://jitpack.io/#commandiron/AnimatableCompose)

Add Animatable Material Components in Android Jetpack Compose. Create basic ui animations painless.

## How it looks

## Usage

### AnimatableText

<table border="0">
<tr>
<td>
    <img src="https://user-images.githubusercontent.com/50905347/195865007-3c1b2670-d0eb-41ae-9a0d-5757ff63779e.gif" width="250" height="530">
</td>
<td>
    
```kotlin
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
```
</td>
</tr>
</table>

### AnimatableBox

<table border="0">
<tr>
<td>
    <img src="https://user-images.githubusercontent.com/50905347/195865007-3c1b2670-d0eb-41ae-9a0d-5757ff63779e.gif" width="250" height="530">
</td>
<td>
    
```kotlin
// Simply create box state and pass it to AnimatableBox
val state = rememberAnimatableBoxState(
    initialSize = DpSize(60.dp, 60.dp), // set initial size
    targetSize = DpSize(Dp.Infinity, 120.dp), // set target size
    initialOffset = DpOffset(x = 0.dp, y = 0.dp), // set initial offset
    targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity) // set target offset
    // Dp.Infinity will take the maximum value according to the screen size
)
AnimatableBox(
    modifier = Modifier
        .border(1.dp, Color.Red)
        .clickable {
            state.animate()
        },
    contentAlignment = Alignment.TopEnd,
    state = state,
) {
    Icon(
        modifier = Modifier.padding(8.dp),
        imageVector = Icons.Default.Add,
        contentDescription = null
    )
}
```
</td>
</tr>
</table>

### AnimatableCard

<table border="0">
<tr>
<td>
    <img src="https://user-images.githubusercontent.com/50905347/195865007-3c1b2670-d0eb-41ae-9a0d-5757ff63779e.gif" width="250" height="530">
</td>
<td>
    
```kotlin
val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(width = 70.dp, height = 70.dp),
    targetSize = DpSize(width = 200.dp, height = 70.dp),
    initialShape = CircleShape,
    targetShape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 0.dp),
    initialOffset = DpOffset(x = 0.dp, y = 0.dp),
    targetOffset = DpOffset(x = - Dp.Infinity, y = - Dp.Infinity)
)
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
</td>
</tr>
</table>

### Multiple Animatable Components at the same time (Shared State)

<table border="0">
<tr>
<td>
    <img src="https://user-images.githubusercontent.com/50905347/195865007-3c1b2670-d0eb-41ae-9a0d-5757ff63779e.gif" width="250" height="530">
</td>
<td>
    
```kotlin
//Create components state
val animatableCardState = rememberAnimatableCardState(
    initialSize = DpSize(80.dp, 80.dp),
    targetSize = DpSize(Dp.Infinity, 120.dp),
    toTargetSizeAnimationSpec = tween(500, 500),
    initialShape = RoundedCornerShape(32.dp),
    targetShape = RoundedCornerShape(0.dp),
    toTargetShapeAnimationSpec = tween(500, 500),
    toTargetAlphaAnimationSpec = tween(500, 500),
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
        animatableIconState.copy( // create state with copy func. for same params.
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
</td>
</tr>
</table>

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
    implementation 'com.github.commandiron:AnimatableCompose:1.0.0'
}
```
