package y2024

import Day
import getLines
import getText
import toColumns
import kotlin.math.abs

fun main() {
    Day3().run()
}

class Day3 : Day(3, 2025) {
    override fun part1(text:String): String {
        val matches = Regex("""mul\((\d{1,3}),(\d{1,3})\)""").findAll(text)
        val sum = matches.sumOf {
            it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }

        return sum.toString()
    }

    override fun part2(text:String): String {
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

        return sum.toString()
    }
}