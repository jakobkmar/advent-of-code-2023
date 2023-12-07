fun main() = day(7) {

    bothParts {
        val order = "A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2"
            .split(", ").map { it.first() }
            .let { o -> if (currentPart == 2) o.filter { it != 'J' } + 'J' else o }
            .reversed()

        fun String.priority(): Int {
            return (if (currentPart == 2) order.drop(1) else listOf('J')).maxOf { repl ->
                val s = this.replace("J", "$repl")
                val size = s.toSet().size
                when {
                    size == 1 -> 6 // five of a kind
                    size == 2 && s.any { c -> s.count { it == c } >= 4 } -> 5 // four of a kind
                    size == 2 -> 4 // full house
                    size == 3 && s.any { c -> s.count { it == c } >= 3 } -> 3 // three of a kind
                    size == 3 -> 2 // two pair
                    size == 4 -> 1 // one pair
                    else -> 0 // high card
                }
            }
        }

        inputLines
            .map { it.split(' ') }
            .sortedWith sort@{ (s1, _), (s2, _) ->
                when {
                    s1.priority() > s2.priority() -> 1
                    s2.priority() > s1.priority() -> -1
                    else -> {
                        s1.forEachIndexed { index, c ->
                            if (order.indexOf(c) > order.indexOf(s2[index]))
                                return@sort 1
                            else if (order.indexOf(c) < order.indexOf(s2[index]))
                                return@sort -1
                        }
                        0
                    }
                }
            }
            .mapIndexed { i, p -> p.last().toInt() * (i + 1) }
            .sum()
    }

    expectPart1 = 6440
    expectPart2 = 5905
}
