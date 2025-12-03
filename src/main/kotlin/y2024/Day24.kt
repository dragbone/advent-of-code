package y2024

import getLines

fun main() {
    Day24().run {
        partOne()
        partTwo()
    }
}

class Day24 {
    fun partOne() {
        val lines = getLines("y2024/d24.txt", stripBlank = false)
        val setup = lines.takeWhile { it.isNotBlank() }
        val logic = lines.drop(setup.count()).filter { it.isNotBlank() }

        val gateValues = mutableMapOf<String, Boolean>()

        setup.forEach {
            val parts = it.split(":").map { it.trim() }
            gateValues[parts[0]] = when (parts[1].toInt()) {
                0 -> false
                1 -> true
                else -> throw NoWhenBranchMatchedException(parts[1])
            }
        }

        var changed: Boolean
        do {
            changed = false
            logic.forEach {
                val parts = it.split("->")
                val target = parts[1].trim()

                val parts2 = parts[0].trim().split(" ")
                val a = parts2[0]
                val op = parts2[1]
                val b = parts2[2]
                if (gateValues.containsKey(a) && gateValues.containsKey(b) && !gateValues.containsKey(target)) {
                    val value = when (op) {
                        "AND" -> gateValues[a]!! && gateValues[b]!!
                        "OR" -> gateValues[a]!! || gateValues[b]!!
                        "XOR" -> gateValues[a]!! xor gateValues[b]!!
                        else -> throw NoWhenBranchMatchedException(op)
                    }
                    gateValues[target] = value
                    changed = true
                }
            }
        } while (changed)

        val sum = gateValues.filter { it.key.startsWith("z") }.toList().sortedByDescending { it.first }.sumOf {
            if (it.second) {
                val id = it.first.substring(1).toInt()
                1L shl id
            } else {
                0L
            }
        }

        println("$sum")
    }

    fun partTwo() {
        val lines = getLines("y2024/d24.txt", stripBlank = false)
        val setup = lines.takeWhile { it.isNotBlank() }
        val logic = lines.drop(setup.count()).filter { it.isNotBlank() }

        val gateValues = mutableMapOf<String, Boolean>()

        setup.forEach {
            val parts = it.split(":").map { it.trim() }
            gateValues[parts[0]] = when (parts[1].toInt()) {
                0 -> false
                1 -> true
                else -> throw NoWhenBranchMatchedException(parts[1])
            }
        }

        data class Op(
            var a: String,
            var b: String,
            var input: List<String>,
            var op: String,
            var t: String,
        )

        val ops = logic.map {
            val parts = it.split("->")
            val target = parts[1].trim()

            val parts2 = parts[0].trim().split(" ")
            val op = parts2[1]
            val operands = listOf(parts2[0], parts2[2]).sorted()
            Op(operands[0], operands[1], operands, op, target)
        }.toList()

        fun p(i: Int) = "$i".padStart(2, '0')

        val wrongs = mutableSetOf<String>()
        (1..44).forEach { id ->
            val cid = p(id)
            val nid = p(id + 1)
            val xor = ops.single { it.a == "x$cid" && it.b == "y$cid" && it.op == "XOR" }
            val p1 = ops.count { xor.t in it.input && it.op == "XOR" } == 1
            val p2 = ops.count { xor.t in it.input && it.op == "AND" } == 1
            val isCorrect = p1 && p2 && ops.count { xor.t in it.input } == 2
            if (!isCorrect) wrongs.add(xor.t)
        }

        (1..44).forEach { id ->
            val cid = p(id)
            val nid = p(id + 1)
            val xor = ops.single { it.a == "x$cid" && it.b == "y$cid" && it.op == "AND" }
            val p1 = ops.count { xor.t in it.input && it.op == "OR" } == 1
            val p2 = ops.count { xor.t in it.input } == 1
            val isCorrect = p1 && p2
            if (!isCorrect) wrongs.add(xor.t)
        }

        (1..44).forEach { id ->
            val cid = p(id)
            val nid = p(id + 1)
            if (ops.count { it.op == "XOR" && it.t == "z$cid" } != 1) {
                wrongs.add("z$cid")
            }
        }

        ops.filter { !it.a.startsWith("x") }
            .groupBy { Pair(it.a, it.b) }
            .filter { it.value.count() == 2 && it.value.count { it.op == "XOR" } == 1 && it.value.count { it.op == "AND" } == 1 }
            .forEach { (_, values) ->
                val target = values.single { it.op == "XOR" }
                if (!target.t.startsWith("z")) {
                    wrongs.add(target.t)
                }
            }

        println(wrongs.sorted().joinToString(","))
    }
}