package secondPart

import kotlin.collections.iterator

fun mutabilityExample() {
    val readOnlyList: List<String> = listOf("a", "b", "c")
//    readOnlyList.add("d") -- fail

    val mutableList: MutableList<String> = mutableListOf("a", "b", "c")
    mutableList.add("d")
    mutableList.removeAt(0)
    mutableList[0] = "e"

    println(mutableList)
}

fun listExample() {
    val empty: List<Int> = emptyList()
    val fruits = listOf("яблоко", "банан", "яблоко")
    val fruits1 = buildList {
        add("яблоко")
        add("банан")
    }
    val mutable = mutableListOf("a", "b")

    println(fruits[0])
    println(fruits.get(1))
    println(fruits.first())
    println(fruits.last())
    println(fruits.indexOf("яблоко"))
    println(fruits.size)

    println(fruits.getOrNull(10))
    //println(fruits[10]) -- fail

    println(fruits.contains("банан"))
    println("киви" in fruits)

    mutable.add("c")
    mutable.add(0, "z")
    mutable.remove("b")
    mutable.removeAt(0)

    for (fruit in fruits) {
        print("$fruit ")
    }
    println()
    for ((i, fruit) in fruits.withIndex()) {
        println("$i: $fruit")
    }

    println(fruits.distinct())
    println(fruits.reversed())
    println(fruits.sorted())
}

fun setExample() {
    val tags: Set<String> = setOf("kotlin", "лекция", "kotlin")
    println(tags)
    println(tags.size)

    val mutable = mutableSetOf(1, 2, 3)

    mutable.add(3)
    println(mutable.add(4))
    println(mutable.add(2))
    mutable.remove(1)

    val allowedUsers = setOf("alice", "bob", "carol")
    println("alice" in allowedUsers)
    println(allowedUsers.contains("alice"))

    val a = setOf(1, 2, 3, 4)
    val b = setOf(3, 4, 5, 6)
    println(a union b)
    println(a intersect b)
    println(a subtract b)
}

fun mapExample() {
    val prices: Map<String, Double> = mapOf(
        "яблоко" to 59.9,
        "банан" to 89.0,
        "вишня" to 199.0
    )
    val prices1: Map<String, Double> = buildMap {
        put("яблоко", 59.9)
        put("яблоко", 59.9)
    }
    val mutable = mutableMapOf("a" to 1)

    println(prices["яблоко"])
    println(prices["киви"]) // null without fail

    println(prices.getOrDefault("киви", 0.0))
    println(prices["киви"] ?: 0.0)

    println(prices.keys)
    println(prices.values)
    println(prices.size)

    println("банан" in prices)
    println(59.9 in prices.values)

    mutable["b"] = 2
    mutable["a"] = 100
    mutable.remove("b")

    for ((fruit, price) in prices) {
        println("$fruit стоит $price руб.")
    }
    prices.forEach { (fruit, price) -> println("$fruit: $price") }
    println(prices.entries)
}

fun functionalOperations() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    println(numbers.filter { it % 2 == 0 })
    println(numbers.map { it * it })

    val result = numbers
        .filter { it > 2 }
        .map { it * 10 }
    println(result)

    println(numbers.sum())
    println(numbers.sumOf { it * 2 })
    println(numbers.maxOrNull()) // if empty will be null
    println(numbers.count { it % 2 == 0 })
    println(numbers.average())

    println(numbers.find { it > 3 })
    println(numbers.any { it > 5 })
    println(numbers.all { it > 0 })
    println(numbers.none { it > 10 })
}

fun main() {
    println("--- изменяемость ---")
    mutabilityExample()

    println("\n--- List ---")
    listExample()

    println("\n--- Set ---")
    setExample()

    println("\n--- Map ---")
    mapExample()

    println("\n--- функциональные операции ---")
    functionalOperations()
}
