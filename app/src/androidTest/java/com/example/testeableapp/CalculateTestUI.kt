package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for TipCalculatorScreen using Jetpack Compose Testing.
 * These tests validate that key components behave correctly when interacted with.
 */
class CalculateTestUi {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test that verifies the tip value updates correctly when the "Round up tip" checkbox is selected.
     * Given a 15% tip on $100, the result should be 15.0. Since it's already whole, no ceil effect is applied.
     */
    @Test
    fun roundTip_updatesTipValue() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")
        composeTestRule.onNodeWithText("Redondear propina").performClick()
        composeTestRule.onNodeWithText("Propina: $15.00").assertExists()
    }


    /**
     * Test that simulates changing the tip percentage via the slider and validates that the tip value
     * updates accordingly.
     * Starts at 15%, swipes right to increase percentage, then verifies that tip changed (e.g. to 22.00).
     */
    @Test
    fun changeSliderPercentage_UpdateTip() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .performTextInput("100")

        composeTestRule
            .onNodeWithText("Propina: $15.00")
            .assertExists()

        composeTestRule
            .onNodeWithText("Porcentaje de propina: 15%")
            .performTouchInput { swipeRight() }

        composeTestRule
            .onNodeWithText("Propina:", substring = true)
            .assertTextContains("22.00")
    }


    /**
     * Test that ensures all key UI elements are visible when the screen loads:
     * - Amount field
     * - Tip percentage text
     * - Number of people section
     */
    @Test
    fun validateElements_onMainScreen() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithText("Monto de la cuenta").assertIsDisplayed()
        composeTestRule.onNodeWithText("Porcentaje de propina: 15%").assertIsDisplayed()
        composeTestRule.onNodeWithText("Número de personas: 1").assertIsDisplayed()
    }

    /**
     * Test that verifies increasing the number of people updates the displayed value.
     * It ensures that clicking "+" correctly reflects "Número de personas: 2"
     */
    @Test
    fun increasingNumOfPeopleUpdated() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")
        composeTestRule.onNodeWithText("Número de personas: 1").assertExists()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("Número de personas: 2").assertExists()
    }

    /**
     * Test that verifies no tip is shown when the amount field is empty.
     * This ensures that the calculation does not proceed with blank input.
     */
    @Test
    fun emptyAmount_noTipDisplayed() {
        composeTestRule.setContent { TipCalculatorScreen() }
        composeTestRule.onNodeWithText("Propina:", substring = true).assertDoesNotExist()
    }

}
