package firstPart

import kotlin.text.iterator

fun whileExample() {
    var count = 5
    while (count > 0) {
        println("while: $count")
        count--
    }
    println()
}

fun doWhileExample() {
    var i = 10
    do {
        println("do-while: i = $i")
        i--
    } while (i > 5)
    println()
}

fun rangeExamples() {
    for (i in 1..5) {
        print("$i ")
    }
    println()

    for (i in 0 until 5) {
        print("$i ")
    }
    println()

    for (i in 0..<5) {
        print("$i ")
    }
    println()

    for (i in 0..10 step 2) {
        print("$i ")
    }
    println()

    for (i in 10 downTo 1 step 3) {
        print("$i ")
    }
    println()

    for (c in "Kotlin") {
        print("$c ")                   // K o t l i n
    }
    println()
}

fun breakAndContinue() {
    for (i in 1..100) {
        if (i % 7 == 0) {
            println("Div by 7: $i")
            break
        }
    }

    for (i in 1..10) {
        if (i % 2 == 0) continue
        print("$i ")
    }
    println()
}

fun labelExample() {
    outer@ for (i in 1..5) {
        for (j in 1..5) {
            if (i * j > 6) {
                println("(i=$i, j=$j)")
                break@outer
            }
            print("${i * j} ")
        }
    }
    println()
}

fun main() {
    println("--- while ---")
    whileExample()

    println("\n--- do-while ---")
    doWhileExample()

    println("\n--- for ---")
    rangeExamples()

    println("\n--- break и continue ---")
    breakAndContinue()

    println("\n--- labels ---")
    labelExample()
}