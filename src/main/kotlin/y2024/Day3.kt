package y2024

import getLines
import getText
import toColumns
import kotlin.math.abs

fun main() {
    Day3().run {
        partOne()
        partTwo()
    }
}

class Day3 {
    fun partOne() {
        val text = getText("y2024/d3.txt")
        val matches = Regex("""mul\((\d{1,3}),(\d{1,3})\)""").findAll(text)
        val sum = matches.sumOf {
            it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }

        println("PART 1: $sum")
    }

    fun partTwo() {
        val text = getText("y2024/d3.txt")
        val matches = Regex("""mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""").findAll(text)
        var enabled = true
        val sum = matches.sumOf {
            when (it.value) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> if (enabled) {
                    return@sumOf it.groupValues[1].toInt() * it.groupValues[2].toInt()
                }
            }
            0
        }

        println("PART 2: $sum")
    }
}