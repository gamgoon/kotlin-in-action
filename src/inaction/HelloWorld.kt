package inaction

fun main(args: Array<String>) {
    println("Hellow, world!")
    println(max(1,2))
    val person = Person("Bob", true)
    println(person.isOk)
    println(person.nickname)
    person.nickname = "우우우"
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

class Person(
    val name: String,
    var isMarried: Boolean
) {
    val isOk: Boolean
    get() {
        return true
    }
    val isNotOk: Boolean
    get() {
        return false
    }
    var nickname: String
    get() = "닉네임"
    set(value) {
        println("Set!!! ${value}")
    }
}