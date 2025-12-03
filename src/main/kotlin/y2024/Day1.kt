package y2024

import getLines
import toColumns
import kotlin.math.abs

fun main() {
    Day1().run {
        partOne()
        partTwo()
    }
}

class Day1 {
    fun partOne() {
        val lines = getLines("y2024/d1.txt", stripBlank = true)
        val cols = lines.toColumns(2) { it.split("   ").map { it.toInt() } }
        val totalDiff = cols[0].sorted().zip(cols[1].sorted()).fold(0) { acc, (a, b) -> acc + abs(a - b) }
        println(totalDiff)
    }

    fun partTwo() {
        val lines = getLines("y2024/d1.txt").filter { it.isNotBlank() }
        val a = lines.map { it.split("   ")[0].toInt() }.sorted()
        val b = lines.map { it.split("   ")[1].toInt() }.groupBy { it }
        val totalDiff = a.fold(0) { acc, x -> acc + x * b[x].orEmpty().size }
        println(totalDiff)
    }
}