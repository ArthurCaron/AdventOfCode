package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day3Star2 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 3))

	override fun getResult(): Int {
		return input
			.asSequence()
			.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
			.map { Rucksack(it.first, it.second) }
			.chunked(3)
			.map { Group(it) }
			.sumOf { it.computePriority() }
	}

	data class Group(
		private val rucksacks: List<Rucksack>
	) {
		private val lowercasePriority = ('a'..'z').mapIndexed { index, character -> character to index + 1 }.associate { it }
		private val uppercasePriority = ('A'..'Z').mapIndexed { index, character -> character to index + 27 }.associate { it }
		private val characterPriority = lowercasePriority + uppercasePriority

		fun computePriority() = characterPriority[findRepeatingChar()] ?: 0

		private fun findRepeatingChar() = rucksacks
				.map { it.firstCompartment + it.secondCompartment }
				.map { it.toSet() }
				.reduce { s1, s2 -> s1.intersect(s2) }
				.first()
	}

	data class Rucksack(
		val firstCompartment: String,
		val secondCompartment: String
	)
}
