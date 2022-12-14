package io.karon.adventofcode.year2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Star2Test {
	@Test
	fun `test input`() {
		val actual = listOf(
			"vJrwpWtwJgWrhcsFMMfFFhFp",
			"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
			"PmmdzqPrVvPwwTWBwg",
			"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
			"ttgJtRGJQctTZtZT",
			"CrZsJsPPZsGzwwsLwLmpwMDw"
		)
			.asSequence()
			.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
			.map { Day3Star2.Rucksack(it.first, it.second) }
			.chunked(3)
			.map { Day3Star2.Group(it) }
			.sumOf { it.computePriority() }
		assertEquals(70, actual)
	}
}
