import kotlin.io.path.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.time.measureTime

abstract class Day(val day: Int, val year: Int) {
    abstract fun part1(text: String): Any
    abstract fun part2(text: String): Any
    val exampleFile = "y$year/d${day}_ex.txt"
    val file = "y$year/d${day}.txt"
    fun run() {
        Path("src/main/resources", exampleFile).run { if (!exists()) createFile() }
        Path("src/main/resources", file).run { if (!exists()) createFile() }
        run {
            println("=== PART 1 ===")
            val duration = measureTime {
                println("Example: ${part1(getText(exampleFile))}")
                println("Solution: ${part1(getText(file))}")
            }
            println("Duration: $duration")
        }
        println()
        run {
            println("=== PART 2 ===")
            val duration = measureTime {
                println("Example: ${part2(getText(exampleFile))}")
                println("Solution: ${part2(getText(file))}")
            }
            println("Duration: $duration")
        }
    }
}