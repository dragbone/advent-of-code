package y2025

import Day

fun main() {
    Day1().run()
}

class Day1 : Day(1, 2025) {
    override fun part1(text: String): Any {
        var dial = 50
        var count = 0
        text.lines().forEach {
            when {
                it.startsWith("L") -> dial -= it.substring(1).toInt()
                it.startsWith("R") -> dial += it.substring(1).toInt()
            }
            dial %= 100
            if (dial == 0) {
                count += 1
            }
        }
        return count
    }

    override fun part2(text: String): Any {
        var dial = 50
        var count = 0
        fun check() {
            if (dial == 0 || dial == 100) {
                count += 1
            }
            dial = (dial + 100) % 100
        }
        text.lines().forEach {
            val numTurns = it.substring(1).toInt()
            when {
                it.startsWith("L") -> repeat(numTurns) {
                    dial -= 1
                    check()
                }

                it.startsWith("R") -> repeat(numTurns) {
                    dial += 1
                    check()
                }
            }
        }
        return count
    }
}