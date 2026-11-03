package secondPart

class PlainUser(val name: String, val age: Int)

data class User(val name: String, val age: Int)

fun comparison() {
    val a = PlainUser("Аня", 20)
    val b = PlainUser("Аня", 20)
    println(a == b)
    println(a)

    val x = User("Аня", 20)
    val y = User("Аня", 20)
    println(x == y)
    println(x)
    println(x.hashCode() == y.hashCode())
}

fun copyExample() {
    val original = User("Аня", 20)
    val older = original.copy(age = 21)

    println(older)
    println(original)
}

fun destructuringExample() {
    val user = User("Борис", 30)

    val (name, age) = user
    println("$name, $age лет")

    val users = listOf(User("Аня", 20), User("Вика", 25))
    for ((n, a) in users) {
        println("$n — $a лет")
    }
}

data class Point(val x: Double = 0.0, val y: Double = 0.0)

fun defaultsExample() {
    val origin = Point()
    val onX = Point(x = 5.0)
    val custom = Point(y = 2.0, x = 1.0)
    println("$origin $onX $custom")
}

fun collectionsExample() {
    val u1 = User("Аня", 20)
    val u2 = User("Аня", 20)

    val unique: Set<User> = setOf(u1, u2)
    println(unique.size)

    val ages: Map<User, Int> = mapOf(u1 to 100)
    println(ages[User("Аня", 20)])

    val plainSet = setOf(PlainUser("Аня", 20), PlainUser("Аня", 20))
    println(plainSet.size)
}


fun main() {
    println("--- обычный класс vs data class ---")
    comparison()

    println("\n--- copy() ---")
    copyExample()

    println("\n--- деструктуризация ---")
    destructuringExample()

    println("\n--- значения по умолчанию ---")
    defaultsExample()

    println("\n--- коллекции ---")
    collectionsExample()
}