package supplement

fun main(args: Array<String>) {
    val l = listOf(1,2,3,4,5,6,7)
    println("l = ${l}")

    // 그룹
    println("l.chunked(size=3)")
    l.chunked(size = 3).forEach { println(it) }

    // 슬라이딩 윈도우
    println("l.windowed(size=3,step=1)")
    l.windowed(size = 3, step = 1).forEach { println(it) }

    // 연속된 2 원소씩 쌍
    println("l.zipWithNext")
    l.zipWithNext { x, y -> println("($x,$y)") }

    // 그룹을 슬라이딩 윈도우로 구현
    fun <T> List<T>.mychunked(size: Int) = windowed(size,size)

    // pairwise를 슬라이딩 윈도우로 구현
    fun <T,R> List<T>.myZipWithNext(f: (T,T) -> R) =
            windowed(size=2, step = 1)
                .filter { it.size == 2 }
                .map { (x,y) -> f(x,y) }

    println("l.mychunked(size=3)")
    l.mychunked(size = 3).forEach { println(it) }

    println("l.myZipWithNext")
    l.myZipWithNext { x, y -> println("($x,$y)") }
}