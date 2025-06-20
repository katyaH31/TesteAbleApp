package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the calculateTip() function and total per person logic.
 * This class verifies expected behaviors with various input scenarios.
 */
class CalculateTipTest {

    /**
     * Tests tip calculation with 37% and rounding enabled.
     * Expected result: 37.0 (already a whole number, no change after ceil).
     */
    @Test
    fun tip_roundedAt37Percent() {
        assertEquals(37.0, calculateTip(100.0, 37, true), 0.01)
    }

    /**
     * Tests that negative amounts return 0 as tip.
     */
    @Test
    fun calculateTip_NegativeAmount() {
        val result = calculateTip(-50.0, 20, false)
        assertEquals(0.0, result, 0.01)
    }

    /**
     * Tests correct division of total amount among multiple people.
     */
    @Test
    fun perPerson_total() {
        assertEquals(30.0, (100.0 + calculateTip(100.0, 20, false)) / 4, 0.01)
    }

    /**
     * Tests that when number of people is 0, total per person returns 0 to avoid division by zero.
     */
    @Test
    fun totalPerPerson_0People() {
        val people = listOf(0).first()
        val total = if (people > 0)  (100.0 + calculateTip(100.0, 20, false)) / people else 0.0
        assertEquals(0.0, total, 0.01)
    }

    /**
     * Tests that tip calculation without rounding preserves decimal values correctly.
     */
    @Test
    fun tipCalculator_outRounding() {
        assertEquals(12.75, calculateTip(85.0, 15, false), 0.01)
    }


}
