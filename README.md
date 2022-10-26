# AnimatableCompose [![](https://jitpack.io/v/commandiron/AnimatableCompose.svg)](https://jitpack.io/#commandiron/AnimatableCompose)

Add Animatable Material Components in Android Jetpack Compose. Create basic ui animations painless.

## How it looks

## Usage

### AnimatableText

<img src="https://user-images.githubusercontent.com/50905347/197984582-1988a82a-db0a-4e8f-a1f7-f0a134b8e45a.gif" width="250" height="530">

<details open>
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
<details open>
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

<img src="https://user-images.githubusercontent.com/50905347/197984666-b660f0b6-d9fb-469c-af08-b88cb2911deb.gif" width="250" height="530">

<details open>
<summary>State</summary>
<br>

        
```kotlin
// Simply create box state and pass it to AnimatableBox
val state = rememberAnimatableBoxState(
    initialSize = DpSize(60.dp, 60.dp), // set initial size
    targetSize = DpSize(Dp.Infinity, 120.dp), // set target size
    initialOffset = DpOffset(x = 0.dp, y = 0.dp), // set initial offset
    targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity) // set target offset
    // Dp.Infinity will take the maximum value according to the screen size
)
```
</details>
<details open>
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

<table border="0">
<tr>
<td>
    <img src="https://user-images.githubusercontent.com/50905347/197984698-12536dc4-9a5b-40e1-9627-484738600b60.gif" width="250" height="530">
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
    <img src="https://user-images.githubusercontent.com/50905347/197984728-7bfe5536-b78e-41e1-91cb-5bc167e51850.gif" width="250" height="530">
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
