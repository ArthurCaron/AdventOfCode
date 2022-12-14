package io.karon.adventofcode.year2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day5Star2Test {
	private val testInput = """
		    [D]    
		[N] [C]    
		[Z] [M] [P]
		 1   2   3 
		
		move 1 from 2 to 1
		move 3 from 1 to 3
		move 2 from 2 to 1
		move 1 from 1 to 2
	""".trimIndent()
		.split("\n")

	@Test
	fun `test input`() {
		val lineOfCrates = testInput
			.filter { it.contains("[") }
			.reversed()
			.map { it.chunked(4).mapIndexed { colIndex, chunk -> colIndex to chunk } }
			.map { list -> list.map { chunk -> Day5Star2.Crate(chunk.first, chunk.second) } }

		val stacks = Array(lineOfCrates.first().last().colIndex + 1) { ArrayDeque<Day5Star2.Crate>() }

		lineOfCrates.forEach { line ->
			line.forEach { crate ->
				if (crate.isNotEmpty) {
					stacks[crate.colIndex].add(crate)
				}
			}
		}

		val instructions = testInput
			.filter { it.startsWith("move") }
			.map { Day5Star2.Instruction(it) }

		instructions.forEach { instruction ->
			val cratesToMove = mutableListOf<Day5Star2.Crate>()
			repeat(instruction.amount) {
				cratesToMove.add(stacks[instruction.origin].removeLast())
			}
			stacks[instruction.destination].addAll(cratesToMove.reversed())
		}

		assertEquals("MCD", stacks.joinToString("") { it.last().name })
	}
}
