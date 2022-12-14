package io.karon.adventofcode.year2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day4Star2Test {
	@Test
	fun `test input`() {
		val actual = listOf(
			"2-4,6-8",
			"2-3,4-5",
			"5-7,7-9",
			"2-8,3-7",
			"6-6,4-6",
			"2-6,4-8"
		)
			.map { it.split(",") }
			.map { (elf1, elf2) -> Day4Star2.ElfPair(Day4Star2.Section(elf1), Day4Star2.Section(elf2)) }
			.filter { it.hasOverlapInSections() }
			.count()
		assertEquals(4, actual)
	}
}
