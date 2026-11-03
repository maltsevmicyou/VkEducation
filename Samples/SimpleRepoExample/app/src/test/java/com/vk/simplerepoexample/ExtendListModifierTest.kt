package com.vk.simplerepoexample

import com.vk.simplerepoexample.ExtendListModifier
import com.vk.simplerepoexample.Logger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ExtendListModifierTest {

    private val initialList = listOf(1, 2, 3)
    private val initialListPlusOne = listOf(2, 3, 4)
    private val initialListMulTwo = listOf(2, 4, 6)
    private val initialListDivideTwo = listOf(0, 1, 1)

    @Before
    fun init() {
        //Init some logic
    }

    @Test
    fun check_plus_one() {
        val logger = mock<Logger>()
        doNothing().whenever(logger).log(initialListPlusOne)

        val listModifier = ExtendListModifier(initialList, logger)

        assertEquals(initialListPlusOne, listModifier.addNumber(1))
        verify(logger).log(initialListPlusOne)
    }

    @Test
    fun check_plus_one_with_when() {
        val logger = mock<Logger>()
        Mockito.doNothing().`when`(logger).log(initialListPlusOne)

        val listModifier = ExtendListModifier(initialList, logger)

        assertEquals(initialListPlusOne, listModifier.addNumber(1))
        verify(logger).log(initialListPlusOne)
    }

    @Test
    fun check_mul_two() {
        val logger = mock<Logger>()
        doNothing().whenever(logger).log(initialListMulTwo)

        val listModifier = ExtendListModifier(initialList, logger)

        assertEquals(initialListMulTwo, listModifier.mulNumber(2))
        verify(logger).log(initialListMulTwo)
    }

    @Test
    fun check_divide_two() {
        val logger = mock<Logger>()
        doNothing().whenever(logger).log(initialListDivideTwo)

        val listModifier = ExtendListModifier(initialList, logger)

        assertEquals(initialListDivideTwo, listModifier.divideNumber(2))
        verify(logger).log(initialListDivideTwo)
    }

    @Test
    fun check_divide_zero() {
        val logger = mock<Logger>()

        val listModifier = ExtendListModifier(initialList, logger)

        assertThrows("Division by zero", ArithmeticException::class.java) {
            listModifier.divideNumber(0)
        }
    }

    @Test
    fun check_logger() {
        val logger = mock<Logger>()
        doNothing().whenever(logger).log(any())

        val listModifier = ExtendListModifier(initialList, logger)

        listModifier.addNumber(2)
        listModifier.mulNumber(2)

        verify(logger, times(2)).log(any())
    }

    @After
    fun destroy() {
        //Destroy some logic
    }
}