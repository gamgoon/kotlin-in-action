package ch06

import org.junit.Assert
import org.junit.Before
import org.junit.Test


fun main(args: Array<String>) {
    printAllCaps("abc")
    printAllCaps(null)
    verifyUserInput(" ")
    verifyUserInput(null)

    printHashCode(43)

    yellAt(PersonInJava(null))
}

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)

    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)

    println(managerName(developer))
    println(managerName(ceo))
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address)

class Person(val name: String, val company: Company)

fun Person.countryName() = company?.address?.country ?: "Unknown"

class MyService {
    fun performActon(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService

    @Before
    fun setup() {
        myService = MyService()
    }

    @Test
    fun testAction() {
        Assert.assertEquals("foo", myService.performActon())
    }
}

fun verifyUserInput(input: String?) {
    if (input.isNullOrEmpty()) {
        println("Please fill in the required fiedlds")
    }
}

fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()

fun <T:Any> printHashCode(t: T) {
    println(t.hashCode())
}

fun yellAt(person: PersonInJava) {
    println((person.name ?: "Anyone").toUpperCase() + "!!!")
}

class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        if (value != null) {
            println(value)
        }
    }

}