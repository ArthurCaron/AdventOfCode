package io.karon.adventofcode.year2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Star1Test {
	@Test
	fun `test input`() {
		val actual = listOf(
			"A Y",
			"B X",
			"C Z"
		)
			.map { it.split(" ") }
			.map { (player1, player2) -> Day2Star1.Round(player1, player2) }
			.fold(0) { totalScore, round -> totalScore + round.computeScore() }
		assertEquals(15, actual)
	}
}
