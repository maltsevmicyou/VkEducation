package com.vk.simplerepoexample

fun interface Logger {
    fun log(list: List<Int>)
}

class ExtendListModifier(
    private val list: List<Int>,
    private val logger: Logger
) {

    fun addNumber(number: Int): List<Number> {
        return list.map { it + number }
            .also { logger.log(it) }
    }

    fun mulNumber(number: Int): List<Number> {
        return list.map { it * number }
            .also { logger.log(it) }
    }

    fun divideNumber(number: Int): List<Number> {
        return list.map { it / number }
            .also { logger.log(it) }
    }

}