package y2025

import getLines
import kotlin.time.measureTime

fun main() {
    Day3().run {
        partOne()
        measureTime {
            partTwo()
        }.also { println("Duration: $it") }
    }
}

class Day3 {
    fun partOne() {
        println("= PART 1 =")
        val lines = getLines("y2025/d3.txt", stripBlank = true)
        val joltage = lines.sumOf {
            val a = it.indexOf(it.substring(0, it.length - 1).maxBy { it })
            val b = it.indexOf(it.substring(a + 1).maxBy { it })
            it.substring(a, a + 1).toInt() * 10 + it.substring(b, b + 1).toInt()
        }
        println(joltage)
    }

    fun partTwo() {
        println("= PART 2 =")
        val lines = getLines("y2025/d3.txt", stripBlank = true)
        val numBatteries = 12
        val joltage = lines.sumOf {
            var batteries = it
            (0 until numBatteries).fold(0L) { acc, i ->
                val maxValue = batteries.substring(0, batteries.length - (numBatteries - 1 - i)).max()
                batteries = batteries.substringAfter(maxValue)
                acc * 10 + maxValue.toString().toInt()
            }
        }
        println(joltage)
    }
}