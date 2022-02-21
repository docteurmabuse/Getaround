package com.tizzone.getaround.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tizzone.getaround.data.network.MockWebServerResponse.MOCK_CAR
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarDetailsTest {
    @get: Rule
    val composeTestRule = createComposeRule()
    private val carData = MOCK_CAR

    @Before
    fun setup() {
        composeTestRule.setContent {
            CarDetails(car = carData, navigateUp = {})
            composeTestRule.mainClock.autoAdvance = true // default
            Thread.sleep(5000)
        }
    }

    @Test
    fun areCareDetailsShown() {
        composeTestRule.onNodeWithText("C3").assertIsDisplayed()
        composeTestRule.onNodeWithText("17€/j").assertIsDisplayed()
        composeTestRule.onNodeWithText("259").assertIsDisplayed()
        composeTestRule.onNodeWithText("Owner").assertIsDisplayed()
        composeTestRule.onNodeWithText("Elmira Sorrell").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Back").assertIsDisplayed()
    }
}
