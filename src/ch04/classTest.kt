package ch04

import java.io.File

fun main(args: Array<String>) {
    val user = User2("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muenchen"
    user.address = "대한민국 서울"
    println(user.address)
    println(user)
    val client1 = Client("홍길동", 1234)
    println(client1)
    val client2 = Client("홍길동", 1234)
    println(client2)
    println(client1 == client2)

    val processed = hashSetOf(Client("홍길동", 1234), Client("홍길동", 1234))
    println(processed)
    println(processed.contains(Client("홍길동", 1234)))

    val client3 = Client2("홍길동", 1234)
    println(client3)
    val client4 = Client2("홍길동", 1234)
    println(client4)
    println(client3 == client4)

    val processed2 = hashSetOf(Client2("홍길동", 1234), Client2("홍길동", 1234))
    println(processed2)
    println(processed2.contains(Client2("홍길동", 1234)))

    val lee = Client("이계명", 4122)
    println(lee.copy(postalCode = 4000))

    val lee2 = Client2("이계명", 4122)
    println(lee2.copy(postalCode = 4000))

    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")

    println(CaseInsensitiveFileComparator.compare(
        File("/User"), File("/user")
    ))

    val subscribingUser = User3.newSubscribingUser("bob@gmail.com")
    val facebookUser = User3.newFacebookUser(123)
    println(subscribingUser.nickname)
}

class User2(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println(
                """
                    Address was changed for $name:
                    "$field" -> "$value".""".trimIndent()
            )
            field = value
        }

}

class Client(val name: String, val postalCode: Int) {
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name && postalCode == other.postalCode
    }
    override fun toString(): String = "Client(name=$name, postalCode=$postalCode)"

    fun copy(name: String = this.name, postalCode: Int = this.postalCode) =
            Client(name, postalCode)
}

data class Client2(val name: String, val postalCode: Int)

class DelegatingCollection<T> : Collection<T> {
    override fun contains(element: T): Boolean = innerList.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean =
        innerList.containsAll(elements)

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun iterator(): Iterator<T> = innerList.iterator()

    private val innerList = arrayListOf<T>()

    override val size: Int get() = innerList.size

}

class DelegatingCollection2<T> (
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList

class CountingSet<T> (
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var objectsAdded = 0
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

class User3 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
                User3(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) =
                User3(getFacebookName(accountId))

        private fun getFacebookName(accountId: Int): String = "test"
    }
}