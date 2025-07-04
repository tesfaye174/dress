package com.example.dress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dress.ui.components.DressApp
import com.example.dress.ui.theme.DressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DressTheme {
                DressApp()
            }
        }
    }
}