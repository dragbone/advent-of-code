package y2025

import Day
import toColumns

fun main() {
    Day6().run()
}

class Day6 : Day(6, 2025) {
    override fun part1(text: String): Any {
        val numColums = text.lines().first().split(" ").count { it.isNotBlank() }
        val cols = text.lineSequence().toColumns(numColums) {
            it.trim().split(Regex("""\s+"""))
        }
        val sum = cols.sumOf { col ->
            when (col.last()) {
                "*" -> col.dropLast(1).fold(1L) { acc, s -> acc * s.toLong() }
                "+" -> col.dropLast(1).sumOf { it.toLong() }
                else -> TODO()
            }
        }
        return sum
    }

    override fun part2(text: String): Any {
        return 0
    }
}