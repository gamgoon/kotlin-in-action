package ch06

import java.io.BufferedReader
import java.io.File
import java.io.StringReader

fun main(args: Array<String>) {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)

    val source: Collection<Int> = arrayListOf(3,4,5,6)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)

    val list = listOf("a", "b", "c")
    printInUppercase(list)

    val letters = Array(26){ i -> ('a' + i).toString()}
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))

    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)
    val squares = IntArray(5) {i -> (i+1) * (i+1)}
    println(squares.joinToString())

    squares.forEachIndexed { index, element ->
        println("Argument $index is: $element")
    }
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        val number = line.toIntOrNull()
        result.add(number)
    }
    return result
}

fun addValidNumbers(numbers: List<Int?>) {
//    var sumOfValidNumbers = 0
//    var invalidNumbers = 0
//    for (number in numbers) {
//        if (number != null) {
//            sumOfValidNumbers += number
//        } else {
//            invalidNumbers++
//        }
//    }
//    println("Sum of valid numbers: $sumOfValidNumbers")
//    println("Invalid numbers: $invalidNumbers")
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid nmbers: ${numbers.size - validNumbers.size}")
}

fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item)
    }
}

fun printInUppercase(list: List<String>) {
    println(CollectionUtils.uppercaseAll(list))
    println(list.first())
}

class FileIndexer : FileContentProcessor {
    /*
    - 일부 파일은 이진 파일이며 이진 파일 안의 내용은 텍스트로 표현할 수 없는 경우가 있으므로 리스트는 널이 될 수 있다.
    - 파일의 각 줄은 널일 수 없으므로 이 리스트의 원소는 널이 될 수 없다.
    - 이 리스트는 파일의 내용을 표현하며 그 내용을 바꿀 필요가 없으므로 읽기 전용이다.
     */
    override fun processContents(path: File,
                                 binaryContents: ByteArray?,
                                 textContents: List<String>?) {

    }
}

class PersonParser : DataParser<Person> {
    /*
    - 호출하는 쪽에서 항상 오류 메시지를 받아야 하므로 List<String>은 널이 되면 안 된다.
    - errors의 원소는 널이 될 수도 있다. output에 들어가는 정보를 파싱하는 고정에서 오류가 발생하지 않으면 그 정보와 연관된 오류 메시지는 널이다.
    - 구현 코드에서 원소를 추가할 수 있어야 하므로 List<String>은 변경 가능해야 한다.
     */
    override fun parseData(input: String,
                           output: MutableList<Person>,
                           errors: MutableList<String>?) {

    }

}