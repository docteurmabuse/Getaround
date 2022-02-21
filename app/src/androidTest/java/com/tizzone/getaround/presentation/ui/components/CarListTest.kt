package com.tizzone.getaround.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tizzone.getaround.data.network.MockWebServerResponse.MOCK_CAR_LIST
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarListTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    private val carsData = MOCK_CAR_LIST

    @Before
    fun setup() {
        composeTestRule.setContent {
            CarList(cars = carsData, onNavigateToCarDetail = {})
        }
        composeTestRule.mainClock.autoAdvance = true // default
        composeTestRule.waitForIdle()
        Thread.sleep(3000)
    }

    @Test
    fun areCarListShown() {
        composeTestRule.onNodeWithText("C3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clio").assertIsDisplayed()
        composeTestRule.onNodeWithText("259").assertIsDisplayed()
        composeTestRule.onNodeWithText("270").assertIsDisplayed()
        composeTestRule.onNodeWithText("17€/j").assertIsDisplayed()
        composeTestRule.onNodeWithText("20€/j").assertIsDisplayed()
    }
}
