package example

import java.lang.IllegalArgumentException

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun main(args: Array<String>) {
    Sum(Sum(Num(1), Num(2)), Num(4))
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(e: Expr): Int =
    when(e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }