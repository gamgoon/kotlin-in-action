package ch11

import sun.jvm.hotspot.utilities.Assert
import java.lang.AssertionError
import java.time.LocalDate
import java.time.Period

fun buildString(
    builderAction: StringBuilder.() -> Unit // 수신 객체가 있는 함수 타입의 파라미터를 선언
): String {
    val sb = StringBuilder()
    sb.builderAction() // StringBuilder 인스턴스를 람다의 수신 객체로 넘긴다
    return sb.toString()
}

fun main(args: Array<String>) {
    val s = buildString {
        this.append("Hello, ") // this 키워드는 StringBuilder 인스턴스를 가리킨다
        append("World!")    // this를 생략해도 묵시적으로 StringBuilder 인스턴스가 수신 객체로 취급된다
    }
    println(s)

    val appendExcl: StringBuilder.() -> Unit =
        { this.append("!")}

    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl()
    println(stringBuilder)
    println(buildString(appendExcl))

    println(createHtml())
    println(createAnotherTable())

    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry")

    val i1 = Issue("IDEA-154446", "IDEA", "Bug", "Major", "Save settings failed")
    val i2 = Issue("KT-12183", "Kotlin", "Feature", "Normal",
        "Intention: convert several calls on the same receiver to with/apply")
    val predicate = ImportantIssuesPredicate("IDEA")
    for (issue in listOf(i1, i2).filter(predicate)) {
        println(issue.id)
    }

    val dependencies = DependencyHandler()
    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }

    "kotlin" should start with "kot"

    println(1.days.ago)
    println(1.days.fromNow)
}

open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()
    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }
    override  fun toString() =
            "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")

fun createHtml() = table {
            tr {
                td { }
            }
        }

fun createAnotherTable() = table {
    for (i in 1..2) {
        tr {
            td {

            }
        }
    }
}

class Greeter(private val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }
}


data class Issue(
    val id: String, val project: String, val type: String,
    val priority: String, val description: String
)

class ImportantIssuesPredicate(private val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == "Bug" &&
                (priority == "Major" || priority == "Critical")
    }
}


class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }

}

interface Matcher<T> {
    fun test(value: T)
}

class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix)) {
            throw AssertionError("String $value does not start with $prefix")
        }
    }
}

object start

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) =
            if (!value.startsWith(prefix))
                throw AssertionError("String $value does not start with $prefix")
            else
                Unit
}

val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this
