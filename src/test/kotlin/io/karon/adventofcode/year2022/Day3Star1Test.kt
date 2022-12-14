package io.karon.adventofcode.year2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Star1Test {
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
			.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
			.map { Day3Star1.Rucksack(it.first, it.second) }
			.sumOf { it.computePriority() }
		assertEquals(157, actual)
	}
}
