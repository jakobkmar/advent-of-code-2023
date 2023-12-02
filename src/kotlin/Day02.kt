fun main() = day(2) {

    /**
     * 6 AM SOLUTION!!!
     * NEEDS MASSIVE CLEANUP!!!
     * DO NOT JUDGE!!!
     */

    part1 {
        val maxVals = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        inputLines
            .map { it.removePrefix("Game ") }
            .sumOf { line ->
                val (idStr, listStr) =  line.split(":").map { it.trim() }
                val id = idStr.toInt()

                listStr.split(";").forEach { subsetStr ->
                    subsetStr.split(",").forEach {
                        val (countStr, col) = it.trim().split(" ")
                        if (countStr.toInt() > maxVals[col]!!) {
                            return@sumOf 0
                        }
                    }
                }

                id
            }
    }

    part2 {
        inputLines
            .sumOf { line ->
                val (_, listStr) =  line.split(":").map { it.trim() }

                val maximums = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0
                )

                listStr.split(";").forEach { subsetStr ->
                    subsetStr.split(",").forEach {
                        val (countStr, col) = it.trim().split(" ")
                        if (maximums[col]!! < countStr.toInt()) {
                            maximums[col] = countStr.toInt()
                        }
                    }
                }

                (maximums["red"]!! * maximums["blue"]!! * maximums["green"]!!)
            }
    }

    expectPart1 = 8
    expectPart2 = 2286
}
