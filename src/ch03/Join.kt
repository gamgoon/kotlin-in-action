@file:JvmName("StringFunctions")
package ch03

// const 는 원시타입과 String 타입의 프로퍼티만 지정할 수 있다
const val UNIX_LINE_SEPARATOR = "\n"

@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    performOperation()
    return result.toString()
}

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)
    println(joinToString(list, ";", "(", ")"))
    println(joinToString(prefix = "{", separator = " _ ", postfix = "}", collection = list))
    println(joinToString(list))
    println(joinToString(list, postfix = "|", prefix = "|"))
    reportOperationCount()

//    println("Kotlin".lastChar())
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)

    val strings: List<String> = listOf("first", "second", "fourteenth")
    println(strings.last())
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println(map)

    val (number, name) = 1 to "one"
    println(number)
    println(name)
    val p = "kotlin" to "one"
    println(p)
    println(p.first)
    println(p.second)
    println(p.toList())
}

var opCount = 0

fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}

//fun String.lastChar(): Char = this.get(this.length - 1)

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)     // getter
    set(value: Char) {          // setter
        this.setCharAt(length - 1, value)
    }
