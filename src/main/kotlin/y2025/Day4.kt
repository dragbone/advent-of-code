package y2025

import Day

fun main() {
    Day4().run()
}

class Day4 : Day(4, 2025) {
    override fun part1(text: String): Any {
        val map = text.lines().mapIndexed { y, line ->
            line.mapIndexed { x, b ->
                if (b == '@') {
                    Pair(x, y)
                } else {
                    null
                }
            }.filterNotNull()
        }.flatten().toSet()
        return map.count { pos ->
            (-1..1).sumOf { y ->
                (-1..1).count { x ->
                    Pair(pos.first + x, pos.second + y) in map
                }
            } <= 4
        }
    }

    override fun part2(text: String): Any {
        val map = text.lines().mapIndexed { y, line ->
            line.mapIndexed { x, b ->
                if (b == '@') {
                    Pair(x, y)
                } else {
                    null
                }
            }.filterNotNull()
        }.flatten().toMutableSet()
        var count = 0
        var change: Boolean
        do {
            change = false
            val remove = mutableSetOf<Pair<Int,Int>>()
            map.forEach { pos ->
                if ((-1..1).sumOf { y ->
                        (-1..1).count { x ->
                            Pair(pos.first + x, pos.second + y) in map
                        }
                    } <= 4) {
                    change = true
                    count += 1
                    remove.add(pos)
                }
            }
            map -= remove
        } while (change)

        return count
    }
}