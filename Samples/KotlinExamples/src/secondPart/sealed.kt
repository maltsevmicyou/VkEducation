package secondPart

sealed class Result

data class Success(val data: String) : Result()
data class Failure(val code: Int, val message: String) : Result()
object Loading : Result()

fun render(result: Result): String = when (result) {
    Loading ->
        "Загрузка..."

    is Success ->
        "Успех: ${result.data}"

    is Failure ->
        "Ошибка ${result.code}: ${result.message}"
}

sealed class Shape {
    abstract val area: Double
    abstract val color: String
}

data class Circle(val radius: Double, override val color: String) : Shape() {
    override val area: Double
        get() = Math.PI * radius * radius
}

data class Rectangle(val width: Double, val height: Double, override val color: String) : Shape() {
    override val area: Double
        get() = width * height
}

fun describeShape(shape: Shape): String = when (shape) {
    is Circle -> "Круг радиуса ${shape.radius}, площадь ${"%.2f".format(shape.area)}"
    is Rectangle -> "Прямоугольник ${shape.width}x${shape.height}, площадь ${"%.2f".format(shape.area)}"
}

sealed interface Event {
    fun logName(): String
}

data class Click(val x: Int, val y: Int) : Event {
    override fun logName() = "клик в ($x, $y)"
}

data class KeyPress(val key: Char) : Event {
    override fun logName() = "нажата клавиша '$key'"
}

data class Scroll(val deltaY: Int) : Event {
    override fun logName() = "скролл на $deltaY"
}

fun processEvent(event: Event) = when (event) {
    is Click -> "Обрабатываем ${event.logName()}"
    is KeyPress -> "Обрабатываем ${event.logName()}"
    is Scroll -> "Обрабатываем ${event.logName()}"
}


fun main() {
    println("--- результат операции ---")
    println(render(Loading))
    println(render(Success("данные получены")))
    println(render(Failure(404, "Не найдено")))

    println("\n--- фигуры ---")
    val shapes: List<Shape> = listOf(
        Circle(2.0, "красный"),
        Rectangle(3.0, 4.0, "синий")
    )
    for (s in shapes) println(describeShape(s))

    println("\n--- события ---")
    val events: List<Event> = listOf(Click(10, 20), KeyPress('a'), Scroll(-3))
    for (e in events) println(processEvent(e))
}
