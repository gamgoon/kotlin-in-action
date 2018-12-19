package ch09

import java.lang.IllegalArgumentException

fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>
        ?: throw IllegalArgumentException("List is expected")
    if (intList is List<Int>) {
        println(intList.sum())
    }
}

inline fun <reified T> isA(value: Any) = value is T

fun main(args: Array<String>) {
    printSum(listOf(1,2,3))
//    printSum(setOf(1,2,3))
//    printSum(listOf("a", "b", "c"))
    println(isA<String>("123"))
    println(isA<String>(123))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())
}