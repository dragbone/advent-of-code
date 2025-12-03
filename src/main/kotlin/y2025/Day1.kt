package y2025

import getLines

fun main() {
    Day1().run {
        partOne()
        partTwo()
    }
}

class Day1 {
    fun partOne() {
        println("= PART 1 =")
        val lines = getLines("y2025/d1.txt", stripBlank = true)
        var dial = 50
        var count = 0
        lines.forEach {
            when {
                it.startsWith("L") -> dial -= it.substring(1).toInt()
                it.startsWith("R") -> dial += it.substring(1).toInt()
            }
            dial = dial % 100
            if (dial == 0) {
                count += 1
            }
        }
        println(count)
    }

    fun partTwo() {
        println("= PART 2 =")
        val lines = getLines("y2025/d1.txt", stripBlank = true)
        var dial = 50
        var count = 0
        fun check() {
            if (dial == 0 || dial == 100) {
                count += 1
            }
            if (dial < 0) dial += 100
            if (dial > 99) dial -= 100
        }
        lines.forEach {
            val count = it.substring(1).toInt()
            when {
                it.startsWith("L") -> repeat(count) {
                    dial -= 1
                    check()
                }

                it.startsWith("R") -> repeat(count) {
                    dial += 1
                    check()
                }
            }
        }
        println(count)
    }
}