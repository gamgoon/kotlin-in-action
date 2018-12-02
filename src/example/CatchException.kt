package example

import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val percentage = 10
    if (percentage !in 0..100) {
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")
    }
    println(percentage)

    val number = 0
    val percentage2 =
            if (number in 0..100) number
            else
                throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")

    println(number)
    println(percentage2)

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    val reader2 = BufferedReader(StringReader("not a number"))
    readNumber2(reader2)
}

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException) {
        return null
    }
    finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    }
    catch (e: NumberFormatException) {
//        return
        null
    }

    println(number)
}