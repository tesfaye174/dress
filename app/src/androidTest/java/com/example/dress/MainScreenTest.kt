package com.example.dress

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGreetingMessage() {
        composeTestRule.setContent {
            MainScreen()
        }

        composeTestRule.onNodeWithText("Welcome to Dress App!").assertExists()
        composeTestRule.onNodeWithText("").performTextInput("Test User")
        composeTestRule.onNodeWithText("Hello, Test User!").assertExists()
    }
}