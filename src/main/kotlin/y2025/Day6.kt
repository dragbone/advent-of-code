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

    private fun transpose(lines: List<String>): List<String> = StringBuffer().apply {
        val maxLength = lines.maxOf { it.length }
        val paddedLines = lines.filter { it.isNotBlank() }.map {
            it + " ".repeat(maxLength - it.length)
        }
        repeat(maxLength) { i ->
            paddedLines.forEach {
                append(it[i])
            }
            append('\n')
        }
    }.toString().lines()

    override fun part2(text: String): Any {
        val cols = transpose(text.lines()).filter { it.isNotBlank() }
        var operand: Char? = null
        var sum = 0L
        var acc = 0L
        cols.forEach { col ->
            val value = col.dropLast(1).trim().toLong()
            if (col.last() != ' ') {
                operand = col.last()
                sum += acc
                acc = value
            } else {
                when (operand) {
                    '*' -> acc *= value
                    '+' -> acc += value
                    else -> throw NoWhenBranchMatchedException(col)
                }
            }
        }
        sum += acc
        return sum
    }
}