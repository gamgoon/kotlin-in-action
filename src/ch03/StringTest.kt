package ch03

fun main(args: Array<String>) {
    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".", "-"))
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePathWithRegex("/Users/yole/kotlin-book/chapter.adoc")
    val kotlinLogo = """|  //
                       .| //
                       .|/ \"""
    println(kotlinLogo)
    println(kotlinLogo.trimMargin("."))
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    var fullName = path.substringAfterLast("/")
    var fileName = fullName.substringBeforeLast(".")
    var extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension")
    }
}