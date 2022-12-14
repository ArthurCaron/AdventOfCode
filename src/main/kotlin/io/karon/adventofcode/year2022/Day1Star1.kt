package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day1Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 1))

	override fun getResult(): Int {
		val elves = input.fold(Elves()) { elves, line ->
			elves.apply(line)
		}
		return elves.computeHighestCalorieCount()
	}

	class Elves {
		private val elfBackPacks = mutableListOf<ElfBackPack>()

		fun computeHighestCalorieCount() = elfBackPacks.maxOf { it.computeTotalCalorieCount() }

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
