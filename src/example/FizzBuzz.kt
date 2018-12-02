package example

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main(args: Array<String>) {
    for (i in 1..100) {
        println(fizzBuzz(i))
    }
}