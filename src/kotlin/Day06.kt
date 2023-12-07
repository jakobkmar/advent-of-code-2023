fun main() = day(6) {

    fun String.lineInts() = when (currentPart) {
        1 -> split(Regex("""\s+""")).drop(1).map { it.toLong() }
        else -> listOf(split(Regex("""\s+""")).drop(1).joinToString("").toLong())
    }

    bothParts {
        val times = inputLines.first().lineInts()
        inputLines.last().lineInts()
            .mapIndexed { index, distance -> times[index] to distance }
            .map { (t, d) -> (0..t).count { (t - it) * it > d } }
            .reduce { acc, i -> acc * i }
    }

    expectPart1 = 288
    expectPart2 = 71503
}
