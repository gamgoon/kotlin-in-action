package ch05


data class Person(val name: String, val age: Int)

class Book(val title: String, val authors: List<String>)

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 31), Person("Bob", 31))
//    val people = emptyList<Person>()
    println(people.maxBy({p: Person -> p.age}))
    println(people.maxBy(){p: Person -> p.age})
    println(people.maxBy {p: Person -> p.age})
    println(people.maxBy {p -> p.age})
    println(people.maxBy {it.age})

    val names = people.joinToString(separator = " ", transform = {p: Person -> p.name})
    println(names)
    println(people.joinToString(separator = " ") {p: Person -> p.name})

    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }

    println(sum(1, 2))

    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")
    printProblemCounts(errors)

    val maxAge = people.maxBy(Person::age)
    println(maxAge)

    println(people.maxBy(Person::age)?.age)

    val books = listOf(Book("책1", listOf("저자1")),
        Book("책2", listOf("저자2-1", "저자2-2")),
        Book("책3", listOf("저자3-1", "저자3-2")))

    println(books.flatMap { it.authors }.toSet())

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })


    println(alphabet())
    println(alphabetWith1())
    println(alphabetWith2())
    println(alphabet2())

    println(alphabetApply())
}



fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun alphabetWith1(): String {
    val result = StringBuilder()
    return with(result) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!")
        this.toString()
    }
}

fun alphabetWith2() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}

fun alphabetApply() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun alphabet2() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}