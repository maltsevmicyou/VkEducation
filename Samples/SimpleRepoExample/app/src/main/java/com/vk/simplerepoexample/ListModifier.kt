package com.vk.simplerepoexample

import android.util.Log
import androidx.annotation.VisibleForTesting

class ListModifier(private val list: List<Int>) {

    @VisibleForTesting // need to show, that this field is used for testing
    var logger: (List<Int>) -> Unit = { Log.d("ListModifier", it.joinToString()) }

    fun addNumber(number: Int): List<Number> {
        return list.map { it + number }
            .also { logger(it) }
    }

    fun mulNumber(number: Int): List<Number> {
        return list.map { it * number }
            .also { logger(it) }
    }

}