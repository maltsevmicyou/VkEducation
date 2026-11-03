package firstPart

enum class OrderStatus {
    NEW,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    TRY
}

enum class Planet(val massKg: Double, val radiusKm: Double) {
    EARTH(5.97e24, 6371.0),
    MARS(6.42e23, 3389.5),
    JUPITER(1.90e27, 69911.0);

    val surfaceGravity: Double
        get() = 6.674e-11 * massKg / (radiusKm * 1000 * radiusKm * 1000)

    fun weightOnPlanet(humanMassKg: Double): Double = humanMassKg * surfaceGravity
}

enum class DayOfWeek(private val isWeekend: Boolean) {
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false),
    THURSDAY(false),
    FRIDAY(false),
    SATURDAY(true),
    SUNDAY(true);

    fun workingHours(): Int = if (isWeekend) {
        0
    } else {
        8
    }
}

fun describeStatus(status: OrderStatus): String = when (status) {
    OrderStatus.NEW -> "Заказ создан, ждём оплату"
    OrderStatus.PAID -> "Оплачен, собираем"
    OrderStatus.SHIPPED -> "Уже в пути"
    OrderStatus.DELIVERED -> "Доставлен"
    OrderStatus.CANCELLED -> "Заказ отменён"
    else -> "неизвестно"
}

fun main() {
    val mars = Planet.MARS
    println("Гравитация на Марсе: ${mars.surfaceGravity} м/с²")
    println("Человек 80 кг на Марсе весит: ${mars.weightOnPlanet(80.0)} Н")
    println()

    for (day in DayOfWeek.entries) {
        println("${day.name}: рабочих часов = ${day.workingHours()}")
    }
    println()

    OrderStatus.entries.forEach { orderStatus ->
        println("${orderStatus.name}: ${describeStatus(orderStatus)}")
    }
}
