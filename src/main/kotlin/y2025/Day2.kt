package y2025

import Day

fun main() {
    Day2().run()
}

class Day2 : Day(2, 2025) {
    override fun part1(text: String): Any {
        val ranges = text.split(",").map { it.split("-").map { it.toLong() } }
        var count = 0L
        ranges.forEach {
            count += (it[0]..it[1])
                .map { it.toString() }
                .filter { it.length % 2 == 0 }
                .filter { it.substring(0, it.length / 2) == it.substring(it.length / 2) }
                .sumOf { it.toLong() }
        }
        return count
    }

    override fun part2(text: String): Any {
        val ranges = text.split(",").map { it.split("-").map { it.toLong() } }
        var count = 0L
        val regex = Regex("""^(\d+)\1+$""")
        ranges.forEach {
            count += (it[0]..it[1])
                .map { it.toString() }
                .filter { regex.matchEntire(it) != null }
                .sumOf { it.toLong() }
        }
        return count
    }
}