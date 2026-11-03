package secondPart

inline fun List<Int>.sortInline() : List<Int> {
    return this.sorted().reversed()
}

fun List<Int>.sort() : List<Int> {
    return this.sorted().reversed()
}

fun allFunctions(number: Int?) {
    val letResult = number?.let {
        print(it)
        it * 5
    }
    //Don't have result
    number?.also {
        print(it)
    }
    val runResult = number?.run {
        print(this)
        this
    }
    //Don't have result
    number?.apply {
        print(this)
    }
    val withResult = with(number) {
        print(this)
        this
    }
}


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(list.sortInline())

    println("функции видимости")
    allFunctions(6)
}