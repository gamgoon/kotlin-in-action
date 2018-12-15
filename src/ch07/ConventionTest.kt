package ch07

import java.time.LocalDate

data class Point(val x: Int, val y: Int) {
//    operator fun plus(other: Point): Point{
//        return Point(x + other.x, y + other.y)
//    }
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Point) return false
        return other.x == x && other.y == y
    }
}

// 연산자를 확장 함수로 정의
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

class Person(
    val firstName: String, val lastName: String
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }

}

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var current = start
            override fun hasNext() =
                    current <= endInclusive
            override fun next() = current.apply {
                current = plusDays(1)
            }
        }

fun main(args: Array<String>) {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)

    println(p1 * 1.5)

    println('a' * 5)

    var point = Point(1, 2)
    point += Point(3, 4)
    println(point)

    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers[0])

    val list = arrayListOf(1, 2)
    list +=3
    val newList = list + listOf(4, 5)
    println(list)
    println(newList)

    println(Point(10, 20) == Point(10, 20))
    println(Point(10, 20) != Point(5, 5))
    println(null == Point(1, 2))

    val ps1 = Person("Alice", "Smith")
    val ps2 = Person("Bob", "Johnson")
    println(ps1 < ps2)

    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(vacation.javaClass)

    val newYear = LocalDate.ofYearDay(2017,1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) { println(dayOff)}
}


