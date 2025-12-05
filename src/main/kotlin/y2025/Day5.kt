package y2025

import Day
import groupedLines

fun main() {
    Day5().run()
}

class Day5 : Day(5, 2025) {
    override fun part1(text: String): Any {
        val segments = text.groupedLines().toList()
        val freshIngredients = segments[0]
            .map { it.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }
        val availableIngredients = segments[1].map { it.toLong() }
        return availableIngredients.count { i -> freshIngredients.any { fresh -> i in fresh } }
    }

    override fun part2(text: String): Any {
        val segments = text.groupedLines().toList()
        val freshIngredientRanges = segments[0]
            .map { it.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }

        val allFreshIngredients = mutableSetOf<LongRange>()
        freshIngredientRanges.forEach { fr ->
            var workingSet = listOf(fr)
            allFreshIngredients.forEach { ex ->
                workingSet = workingSet.flatMap { w ->
                    sequence {
                        if (w.start > ex.endInclusive || w.endInclusive < ex.start) {
                            yield(w)
                        } else {
                            if (w.start < ex.start) {
                                yield(w.start..(ex.start - 1))
                            }
                            if (w.endInclusive > ex.endInclusive) {
                                yield((ex.endInclusive + 1)..w.endInclusive)
                            }
                        }
                    }
                }
            }
            allFreshIngredients.addAll(workingSet)
        }

        return allFreshIngredients.sumOf { it.endInclusive - it.start + 1L }
    }
}