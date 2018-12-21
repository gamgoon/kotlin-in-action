package ch10

import org.junit.Assert
import org.junit.Test
import ru.yole.jkid.CustomSerializer
import ru.yole.jkid.JsonExclude
import ru.yole.jkid.JsonName
import ru.yole.jkid.deserialization.deserialize
import ru.yole.jkid.serialization.serialize

class MyTest {
    @Test
    fun testTrue() {
        Assert.assertTrue(true)
    }
}

@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {

}

data class Person(
    @JsonName("alias") val name: String,
    @JsonExclude val age: Int? = null)

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    println(serialize(person))

    val json = """{"age": 29, "alias": "Alice"}"""
    println(deserialize<Person>(json))
}