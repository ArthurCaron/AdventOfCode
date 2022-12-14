package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day4Star2 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 4))

	override fun getResult(): Int {
		return input
			.map { it.split(",") }
			.map { (elf1, elf2) -> ElfPair(Section(elf1), Section(elf2)) }
			.filter { it.hasOverlapInSections() }
			.count()
	}

	data class ElfPair(
		val elf1Section: Section,
		val elf2Section: Section
	) {
		fun hasOverlapInSections() = elf1Section.ids.intersect(elf2Section.ids).isNotEmpty() || elf2Section.ids.intersect(elf1Section.ids).isNotEmpty()
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
