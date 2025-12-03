import kotlin.time.measureTime

abstract class Day(val day: Int, val year: Int) {
    abstract fun part1(text: String): Any
    abstract fun part2(text: String): Any
    fun run() {
        run {
            println("=== PART 1 ===")
            val duration = measureTime {
                println("Example: ${part1(getText("y$year/d${day}_ex.txt"))}")
                println("Solution: ${part1(getText("y$year/d$day.txt"))}")
            }
            println("Duration: $duration")
        }
        println()
        run {
            println("=== PART 2 ===")
            val duration = measureTime {
                println("Example: ${part2(getText("y$year/d${day}_ex.txt"))}")
                println("Solution: ${part2(getText("y$year/d$day.txt"))}")
            }
            println("Duration: $duration")
        }
    }
}