package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day3Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 3))

	override fun getResult(): Int {
		return input
			.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
			.map { Rucksack(it.first, it.second) }
			.sumOf { it.computePriority() }
	}

	data class Rucksack(
		private val firstCompartment: String,
		private val secondCompartment: String
	) {
		private val lowercasePriority = ('a'..'z').mapIndexed { index, character -> character to index + 1 }.associate { it }
		private val uppercasePriority = ('A'..'Z').mapIndexed { index, character -> character to index + 27 }.associate { it }
		private val characterPriority = lowercasePriority + uppercasePriority

		fun computePriority() = characterPriority[findRepeatingChar()] ?: 0

		private fun findRepeatingChar() = firstCompartment.toSet().intersect(secondCompartment.toSet()).first()
	}
}
