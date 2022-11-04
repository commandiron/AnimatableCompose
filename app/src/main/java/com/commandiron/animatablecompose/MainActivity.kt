package com.commandiron.animatablecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.commandiron.animatablecompose.ui.theme.AnimatableComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatableComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFEFDF8)
                ) {}
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Show8InfoCard()
                }
            }
        }
    }
}