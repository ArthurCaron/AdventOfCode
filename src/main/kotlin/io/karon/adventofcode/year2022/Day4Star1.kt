package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day4Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 4))

	override fun getResult(): Int {
		return input
			.map { it.split(",") }
			.map { (elf1, elf2) -> ElfPair(Section(elf1), Section(elf2)) }
			.filter { it.oneSectionFullyContainedByTheOther() }
			.count()
	}

	data class ElfPair(
		val elf1Section: Section,
		val elf2Section: Section
	) {
		fun oneSectionFullyContainedByTheOther() = elf1Section.ids.subtract(elf2Section.ids).isEmpty() || elf2Section.ids.subtract(elf1Section.ids).isEmpty()
	}

	data class Section(
		private val _ids: String
	) {
		val ids = toIntRange()

		private fun toIntRange(): IntRange {
			val (start, end) = _ids.split("-")
			return IntRange(start.toInt(), end.toInt())
		}
	}
}
