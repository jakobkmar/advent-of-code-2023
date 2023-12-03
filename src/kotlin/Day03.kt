fun main() = day(3) {

    bothParts {
        val grid = inputLines.readGrid()

        val numbers = ArrayList<Int>()
        val gears = LinkedHashMap<Pos, MutableList<Int>>()

        inputLines.forEachIndexed { lineIndex, line ->
            var currentNum = ""
            val addToGears = LinkedHashSet<Pos>()
            var isValid = false
            fun checkout() {
                if (isValid) {
                    numbers += currentNum.toInt()
                    addToGears.forEach { gear ->
                        gears.getOrPut(gear) { ArrayList() }
                            .add(currentNum.toInt())
                    }
                    isValid = false
                }
                currentNum = ""
                addToGears.clear()
            }
            line.forEachIndexed { charIndex, char ->
                val pos = Pos(charIndex, lineIndex)
                if (char.isDigit()) {
                    currentNum += char
                    for (adj in pos.adjacent) {
                        val c = grid[adj] ?: continue
                        if (c == '.') continue
                        if (c == '*') addToGears += adj
                        if (!c.isDigit()) isValid = true
                    }
                } else checkout()
            }
            checkout()
        }

        if (currentPart == 1) {
            numbers.sum()
        } else {
            gears.entries
                .filter { it.value.size == 2 }
                .sumOf { it.value.first() * it.value.last() }
        }
    }

    expectPart1 = 4361
    expectPart2 = 467835
}
