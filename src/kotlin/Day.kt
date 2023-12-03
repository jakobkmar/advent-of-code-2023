@file:Suppress("MemberVisibilityCanBePrivate")

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds

fun day(number: Int, scope: Day.() -> Unit) {
    Day(number, scope).run()
}

class Day(private val number: Int, val scope: Day.() -> Unit) {
    private fun inputFileName(extra: String = "") = "Day${number.toString().padStart(2, '0')}${extra}.txt"

    var inputString: String = ""
        private set(value) {
            field = value
            inputLines = value.lines()
        }
    var inputLines: List<String> = emptyList()

    var isTestRun = false
    var currentPart = 1

    private var part1Block: (() -> Any?)? = null
    private var part2Block: (() -> Any?)? = null

    var expectPart1: Any? = null
    var expectPart2: Any? = null

    private val terminal = Terminal()

    fun part1(block: () -> Any?) {
        part1Block = block
    }

    fun part2(block: () -> Any?) {
        part2Block = block
    }

    fun bothParts(block: () -> Any?) {
        part1Block = block
        part2Block = block
    }

    private fun testPart(part: (() -> Any?)?, expected: Any?) {
        if (part != null && expected != null) {
            val partName = "testPart${currentPart}"
            val actual = part.invoke()
            if (actual != expected) {
                terminal.println("${TextColors.red("Failed")} test for $partName! Expected '$expected' but got '$actual' instead")
            } else {
                terminal.println("The result of $partName is ${TextColors.green("correct")}.")
            }
        }
    }

    private fun runPart(part: (() -> Any?)?) {
        if (part != null) {
            val result: Any?
            val time = measureNanoTime {
                result = part.invoke()
            }
            val msTime = TextColors.brightMagenta("(${time.nanoseconds})")
            terminal.println("The result of part${currentPart} is ${TextStyles.bold(TextColors.brightCyan(result.toString()))} $msTime")
        }
    }

    fun run() {
        println("Running ${TextColors.brightMagenta("Day $number")}")

        println()
        terminal.println(TextColors.gray("Running against tests..."))
        println()

        isTestRun = true
        inputString = this::class.java.getResourceAsStream(inputFileName("_test"))!!.bufferedReader().readText()
        currentPart = 1
        this.scope()
        testPart(part1Block, expectPart1)
        this::class.java.getResourceAsStream(inputFileName("_test_02"))
            ?.let { inputString = it.bufferedReader().readText() }
        currentPart = 2
        this.scope()
        testPart(part2Block, expectPart2)
        isTestRun = false

        println()
        terminal.println(TextColors.gray("Running against real input..."))
        println()

        inputString = this::class.java.getResourceAsStream(inputFileName())!!.bufferedReader().readText()
        currentPart = 1
        this.scope()
        runPart(part1Block)
        currentPart = 2
        this.scope()
        runPart(part2Block)
    }

    fun List<String>.readGrid() = buildMap {
        this@readGrid.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                put(Pos(charIndex, lineIndex), char)
            }
        }
    }
}
