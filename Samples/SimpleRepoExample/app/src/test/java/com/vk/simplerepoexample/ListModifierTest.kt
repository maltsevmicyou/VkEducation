package com.vk.simplerepoexample

import com.vk.simplerepoexample.ListModifier
import org.junit.After
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class ListModifierTest {

    private val initialList = listOf(1, 2, 3)
    private val initialListPlusOne = listOf(2, 3, 4)
    private val initialListMulTwo = listOf(2, 4, 6)

    @Before
    fun init() {
        //Init some logic
    }

//    @Test //Fail, because Log.d is not implemented
//    fun check_plus_one_failed() {
//        val listModifier = ListModifier(initialList)
//        assertEquals(initialListPlusOne, listModifier.addNumber(1))
//    }

    @Test
    fun check_plus_one() {
        val listModifier = ListModifier(initialList)
        listModifier.logger = {}
        assertEquals(initialListPlusOne, listModifier.addNumber(1))
    }

    @Test
    fun check_mul_two() {
        val listModifier = ListModifier(initialList)
        listModifier.logger = {}
        assertEquals(initialListMulTwo, listModifier.mulNumber(2))
    }

    @Test
    fun check_logger() {
        val listModifier = ListModifier(initialList)
        var counter = 0
        listModifier.logger = { counter++ }
        listModifier.addNumber(1)
        assertEquals(1, counter)
    }

    @After
    fun destroy() {
        //Destroy some logic
    }
}