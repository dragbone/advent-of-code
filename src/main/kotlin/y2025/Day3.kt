package y2025

import Day
import lines

fun main() {
    Day3().run()
}

class Day3 : Day(3, 2025) {
    override fun part1(text: String): Any = text.lines(stripBlank = true).sumOf {
        val a = it.indexOf(it.substring(0, it.length - 1).max())
        val b = it.indexOf(it.substring(a + 1).max())
        it.substring(a, a + 1).toInt() * 10 + it.substring(b, b + 1).toInt()
    }

    private val numBatteries = 12
    override fun part2(text: String): Any = text.lines(stripBlank = true).sumOf {
        var batteries = it
        (0 until numBatteries).fold(0L) { acc, i ->
            val maxValue = batteries.take(batteries.length - (numBatteries - 1 - i)).max()
            batteries = batteries.substringAfter(maxValue)
            acc * 10 + maxValue.toString().toInt()
        }
    }
}