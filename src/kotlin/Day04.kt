import kotlin.math.pow

fun main() = day(4) {

    val matches = inputLines
        .map { it.split(":").last().trim() }
        .map { line ->
            val lists = line.split("|")
                .map { s -> s.trim().split(Regex("""\s+""")).map { it.toInt() } }
            lists.first() to lists.last()
        }
        .map { pair -> pair.second.count { it in pair.first } }

    part1 {
        matches.sumOf { 1L shl (it - 1) }
    }

    part2 {
        val scratched = HashMap<Int, Long>()
        fun checkCard(index: Int): Long =
            scratched.getOrPut(index)
            { ((index + 1)..(index + matches[index])).sumOf(::checkCard) + 1 }
        matches.indices.sumOf(::checkCard)
    }

    expectPart1 = 13L
    expectPart2 = 30L
}
