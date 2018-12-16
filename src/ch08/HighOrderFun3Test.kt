package ch08

data class Person3(val name: String, val age: Int)

val people = listOf(Person3("Alice", 29), Person3("Bob", 31))

fun lookForAlice(people: List<Person3>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }

    }
    println("Alice is not found")
}

fun lookForAlice2(people: List<Person3>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

fun lookForAlice3(people: List<Person3>) {
    people.forEach label@{
        if (it.name == "Alice") return@label
    }
    println("Alice might be somewhere")
}

fun lookForAlice4(people: List<Person3>) {
    people.forEach {
        if (it.name == "Alice") return@forEach
    }
    println("Alice might be somewhere")
}

fun lookForAlice5(people: List<Person3>) {
    people.forEach(fun (person) {
        if (person.name == "Alice") return
        println("${person.name} is not Alice")
    })
}

fun main(args: Array<String>) {
    lookForAlice5(people)
}