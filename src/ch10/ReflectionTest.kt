package ch10

import kotlin.reflect.KFunction
import kotlin.reflect.full.memberProperties

var counter = 0
fun foo(x: Int) = println(x)
fun sum(x: Int, y: Int) = x + y

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    val kClass = person.javaClass.kotlin

    println(kClass)
    println(kClass.simpleName)
    kClass.memberProperties.forEach { println(it.name) }

    val kFunction = ::foo
    kFunction.call(42)

    val kFunction1 = ::sum
    println(kFunction1.invoke(1, 2) + kFunction1(3, 4))

//    kFunction1(1)
    val kProperty = ::counter
    kProperty.setter.call(21)
    println(kProperty.get())

    val memberProperty = Person::age
    println(memberProperty.get(person))


}
