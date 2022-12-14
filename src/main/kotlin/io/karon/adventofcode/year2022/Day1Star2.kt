package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day1Star2 : Star {
	override val expectedResult: Int get() = super.expectedResult

	override fun getResult(): Int {
		val input = getAllLinesFromInput(EventDay(2022, 1))
		val elves = input.fold(Elves()) { elves, line ->
			elves.apply(line)
		}
		return elves.computeSumOfThreeHighestCalorieCounts()
	}

	class Elves {
		private val elfBackPacks = mutableListOf<ElfBackPack>()

		fun computeSumOfThreeHighestCalorieCounts() = elfBackPacks
			.map { it.computeTotalCalorieCount() }
			.sortedDescending()
			.take(3)
			.sum()

		fun apply(line: String): Elves {
			if (line.isBlank()) {
				newLine()
			} else {
				add(line)
			}
			return this
		}

		private fun newLine() {
			elfBackPacks.add(ElfBackPack())
		}

		private fun add(calorie: String) {
			if (elfBackPacks.isEmpty()) {
				newLine()
			}
			elfBackPacks.last().add(calorie.toInt())
		}
	}

	class ElfBackPack {
		private val calories: MutableList<Int> = mutableListOf()

		fun add(calorie: Int) {
			calories.add(calorie)
		}

		fun computeTotalCalorieCount() = calories.sum()
	}
}
