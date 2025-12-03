package y2024

import getLines
import toColumns

fun main() {
    Template().run {
        partOne()
        partTwo()
    }
}

class Template {
    fun partOne() {
        val lines = getLines("y2024/d25.txt", stripBlank = false).toList()
        val locks = mutableListOf<List<Int>>()
        val keys = mutableListOf<List<Int>>()
        println()
        lines.chunked(8).map { it.take(7) }.map {
            val isLock = it.first() == "#####"
            val counts = it.asSequence().toColumns(5) { it.toList() }.map { it.count { it == '#' }-1 }
            if (isLock) {
                println("Lock $counts")
                locks.add(counts)
            } else {
                println("Key $counts")
                keys.add(counts)
            }
        }

        val numMatches = locks.sumOf { lock ->
            keys.count { key ->
                lock.zip(key).none { (l, k) -> l + k > 5 }
            }
        }

        println("= PART 1 =")
        println("$numMatches")
    }

    fun partTwo() {
        val lines = getLines("y2024/d25.txt", stripBlank = false)

        println("= PART 2 =")
    }
}