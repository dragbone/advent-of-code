package y2025

import Day
import groupedLines
import kotlin.math.max
import kotlin.math.min

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
        val segments = text.groupedLines()
        val freshIngredientRanges = segments.first()
            .map { it.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }

        val mergedFreshIngredients = mutableSetOf<LongRange>()
        freshIngredientRanges.forEach { fr ->
            var thisRange = fr
            mergedFreshIngredients.removeIf { ex ->
                if (thisRange.start <= ex.endInclusive && thisRange.endInclusive >= ex.start) {
                    thisRange = min(thisRange.start, ex.start)..max(thisRange.endInclusive, ex.endInclusive)
                    true
                } else {
                    false
                }
            }
            mergedFreshIngredients.add(thisRange)
        }

        return mergedFreshIngredients.sumOf { it.endInclusive - it.start + 1L }
    }
}