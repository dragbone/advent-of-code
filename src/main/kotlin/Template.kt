fun main() {
    Template().run {
        partOne()
        partTwo()
    }
}

class Template {
    fun partOne() {
        println("= PART 1 =")
        val lines = getLines("y2025/d1.txt", stripBlank = true)
        println("<solution>")
    }

    fun partTwo() {
        println("= PART 2 =")
        val lines = getLines("y2025/d1.txt", stripBlank = true)
        println("<solution>")
    }
}