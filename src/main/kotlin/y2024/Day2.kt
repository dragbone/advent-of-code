package y2024

import getLines
import kotlin.math.abs

fun main() {
    Day2().run {
        partOne()
        partTwo()
    }
}

class Day2 {
    fun partOne() {
        val lines = getLines("y2024/d2.txt", stripBlank = true)
        val safe = lines.count { line ->
            val diffs = line.split(" ").map { it.toInt() }.zipWithNext().map { (a, b) -> a - b }
            diffs.all { abs(it) in 1..3 } && (diffs.all { it > 0 } || diffs.all { it < 0 })
        }
        println(safe)
    }

    fun partTwo() {
        val lines = getLines("y2024/d2.txt", stripBlank = true)
        val safe = lines.count { line ->
            val values = line.split(" ")
            values.mapIndexed { index, _ ->
                val values = values.take(index) + values.drop(index + 1)
                val diffs = values.map { it.toInt() }.zipWithNext().map { (a, b) -> a - b }
                diffs.all { abs(it) in 1..3 } && (diffs.all { it > 0 } || diffs.all { it < 0 })
            }.any { it }
        }
        println(safe)
    }
}