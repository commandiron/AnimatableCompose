# AnimatableCompose [![](https://jitpack.io/v/commandiron/AnimatableCompose.svg)](https://jitpack.io/#commandiron/AnimatableCompose)

Add Animatable Material Components in Android Jetpack Compose. Create basic ui animations painless.

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
        state = state // pass state.
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
    initialSize = DpSize(60.dp, 60.dp), // set initial size.
    targetSize = DpSize(Dp.Infinity, 120.dp), // set target size.
    initialOffset = DpOffset(x = 0.dp, y = 0.dp), // set initial offset.
    targetOffset = DpOffset(x = 0.dp, y = - Dp.Infinity) // set target offset.
    // Dp.Infinity will take the maximum value according to the screen size
)
AnimatableBox(
    modifier = Modifier
        .border(1.dp, Color.Red)
        .clickable {
            state.animate() // animate
        },
    contentAlignment = Alignment.TopEnd,
    state = state, // pass state
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
