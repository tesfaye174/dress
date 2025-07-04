package com.example.dress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dress.ui.screens.HomeScreen
import com.example.dress.ui.screens.WardrobeScreen
import com.example.dress.ui.screens.RecommendationsScreen
import com.example.dress.ui.theme.DressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DressTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DressApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DressApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToWardrobe = { navController.navigate("wardrobe") },
                onNavigateToRecommendations = { navController.navigate("recommendations") }
            )
        }
        composable("wardrobe") {
            WardrobeScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable("recommendations") {
            RecommendationsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DressAppPreview() {
    DressTheme {
        DressApp()
    }
}