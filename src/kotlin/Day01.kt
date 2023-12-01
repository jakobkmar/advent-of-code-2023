fun main() = day(1) {

    val numberNames =
        listOf("one", "two",  "three",  "four",  "five",  "six",  "seven",  "eight",  "nine")

    bothParts {
        inputLines.sumOf { line ->
            val nums = ArrayDeque<Int>()

            line.forEachIndexed { cIndex, c ->
                if (c.isDigit())
                    nums.add(c.digitToInt())
                if (currentPart == 2)
                    numberNames.forEachIndexed { index, digit ->
                        if (line.drop(cIndex).startsWith(digit))
                            nums.add(index + 1)
                    }
            }

            "${nums.first()}${nums.last()}".toInt()
        }
    }

    expectPart1 = 142
    expectPart2 = 281
}
