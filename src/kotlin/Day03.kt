fun main() = day(3) {

    /*
     * 6 AM SOLUTION!!!
     * NEEDS MASSIVE CLEANUP!!!
     * DO NOT JUDGE!!!
     */

    bothParts {
        val grid = LinkedHashMap<Pos, Char>()
        inputLines.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                grid[Pos(charIndex, lineIndex)] = char
            }
        }

        val numbers = ArrayList<Int>()
        val gears = LinkedHashMap<Pos, MutableList<Int>>()

        inputLines.forEachIndexed { lineIndex, line ->
            var currentNum = ""
            val currentGears = LinkedHashSet<Pos>()
            var isValid = false
            fun checkout() {
                if (currentNum.isEmpty()) return
                if (isValid) {
                    numbers += currentNum.toInt()
                    currentGears.forEach { gear ->
                        gears.getOrPut(gear) { ArrayList() }
                            .add(currentNum.toInt())
                    }
                    isValid = false
                }
                currentNum = ""
                currentGears.clear()
            }
            line.forEachIndexed { charIndex, char ->
                val pos = Pos(charIndex, lineIndex)
                if (char.isDigit()) {
                    currentNum += char
                    if (pos.adjacent.any { grid[it] != '.' && grid[it]?.isDigit()?.not() == true }) {
                        isValid = true
                    }
                    pos.adjacent.forEach {
                        if (grid[it] == '*')
                            currentGears += it
                    }
                } else {
                    checkout()
                }
            }
            checkout()
        }

        if (currentPart == 1) {
            numbers.sum()
        } else {
            gears.entries
                .filter { it.value.size == 2 }
                .sumOf { it.value[0] * it.value[1] }
        }
    }

    expectPart1 = 4361
    expectPart2 = 467835
}
