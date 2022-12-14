package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput
import java.util.*
import kotlin.collections.ArrayDeque

class Day5Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 5))

	override fun getResult(): Any {
		val lineOfCrates = input
			.filter { it.contains("[") }
			.reversed()
			.map { it.chunked(4).mapIndexed { colIndex, chunk -> colIndex to chunk} }
			.map { list -> list.map { chunk -> Crate(chunk.first, chunk.second) } }

		val stacks = Array(lineOfCrates.first().last().colIndex + 1) { ArrayDeque<Crate>() }

		lineOfCrates.forEach { line ->
			line.forEach { crate ->
				if (crate.isNotEmpty) {
					stacks[crate.colIndex].add(crate)
				}
			}
		}

		val instructions = input
			.filter { it.startsWith("move") }
			.map { Instruction(it) }

		instructions.forEach { instruction ->
			repeat(instruction.amount) {
				stacks[instruction.destination].add(stacks[instruction.origin].removeLast())
			}
		}

		return stacks.joinToString("") { it.last().name }
	}

	data class Crate(
		val colIndex: Int,
		private val _name: String
	) {
		val isNotEmpty = _name.isNotBlank()
		val name = if (isNotEmpty) _name.trim()[1].toString() else ""
	}

	data class Instruction(
		private val _instruction: String
	) {
		private val split = _instruction.split(" ")
		val amount = split[1].toInt()
		val origin = split[3].toInt() - 1
		val destination = split[5].toInt() - 1
	}
}
