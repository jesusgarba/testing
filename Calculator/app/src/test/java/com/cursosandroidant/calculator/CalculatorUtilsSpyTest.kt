package com.cursosandroidant.calculator

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsSpyTest {

    @Spy
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }


    @Test
    fun calc_callAddPoint_validSecondPoint_noReturns() {
        val operation = "3.5x2"
        var isCorrect = false
        val operator = "x"
        calculatorUtils.addPoint(operation) {
            isCorrect = true
        }
        assertTrue(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator,operation)

    }

    @Test
    fun calc_callAddPoint_invalidSecondPoint_noReturns() {
        val operation = "3.5x2."
        val operator = "x"
        var isCorrect = false


        calculatorUtils.addPoint(operation) {
            isCorrect = true
        }
        assertFalse(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator,operation)
    }
}