package ch04

import java.io.Serializable

interface Clickable {
    fun click()

    fun showOff() = println("I'm clickable!")
}

class Button : Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    override fun click() = println("I was clicked")
}

fun main(args: Array<String>) {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()

    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
}

interface Focusable {
    fun setFocus(b: Boolean) =
            println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

open class RichButton : Clickable {

    fun disable() {}

    open fun animate() {}

    final override fun click() {}

}

abstract class Animated {
    abstract fun animate()

    open fun stopAnimating() {

    }

    fun animateTwice() {

    }
}

internal open class TalkativeButton: Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

internal fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
}

interface State: Serializable

interface View {
    fun getCurrentState() : State
    fun restoreState(state: State)
}

class Button2 : View {
    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {
    }

    class ButtonState : State {}
}

class Outer {
    inner class Innter {
        fun getOuterReference() : Outer = this@Outer
    }
}

//interface Expr

//class Num(val value: Int) : Expr
//
//class Sum(val left: Expr, val right: Expr) : Expr

sealed class Expr {
    class Num(val value: Int) : Expr()

    class Sum(val left: Expr, val right: Expr) : Expr()
}
fun eval(e: Expr) : Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.right) + eval(e.left)
//            else -> throw IllegalArgumentException("Unknown expression")
        }

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String = "nickname"
}
