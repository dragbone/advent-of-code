package y2025

import getLines

fun main() {
    Day2().run {
        partOne()
        partTwo()
    }
}

class Day2 {
    fun partOne() {
        println("= PART 1 =")
        val lines = getLines("y2025/d2.txt", stripBlank = true)
        val line = lines.first()
        val ranges = line.split(",").map { it.split("-").map { it.toLong() } }
        var count = 0L
        ranges.forEach {
            count += (it[0]..it[1])
                .map { it.toString() }
                .filter { it.length % 2 == 0 }
                .filter { it.substring(0, it.length / 2) == it.substring(it.length / 2) }
                .sumOf { it.toLong() }
        }
        println("$count")
    }

    fun partTwo() {
        println("= PART 2 =")
        val lines = getLines("y2025/d2.txt", stripBlank = true)
        val line = lines.first()
        val ranges = line.split(",").map { it.split("-").map { it.toLong() } }
        var count = 0L
        fun isSilly(input: String): Boolean {
            return (1..input.length / 2).filter { input.length % it == 0 }.any { cs ->
                input.chunked(cs).distinct().size == 1
            }
        }
        ranges.forEach {
            println("${it[0]}..${it[1]}")
            count += (it[0]..it[1])
                .map { it.toString() }
                .filter { isSilly(it) }
                .sumOf {
                    println(" > $it")
                    it.toLong()
                }
        }
        println("$count")
    }
}