package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day2Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult
	val input = getAllLinesFromInput(EventDay(2022, 2))

	override fun getResult(): Int {
		return input
			.map { it.split(" ") }
			.map { (player1, player2) -> Round(player1, player2) }
			.fold(0) { totalScore, round -> totalScore + round.computeScore() }
	}

	data class Round(
		private val player1: String,
		private val player2: String
	) {
		private val player1Symbol = player1.toSymbol()
		private val player2Symbol = player2.toSymbol()

		fun computeScore() =
			player2Symbol.score + when (player1Symbol) {
				player2Symbol.whatCrushesMe() -> 0
				player2Symbol.whatICrush() -> 6
				else -> 3
			}

		private fun String.toSymbol() =
			mapOf(
				"A" to Symbol.ROCK,
				"B" to Symbol.PAPER,
				"C" to Symbol.SCISSORS,
				"X" to Symbol.ROCK,
				"Y" to Symbol.PAPER,
				"Z" to Symbol.SCISSORS
			)[this] ?: Symbol.ROCK
	}

	enum class Symbol(val score: Int) {
		ROCK(1),
		PAPER(2),
		SCISSORS(3);

		fun whatCrushesMe() = values()[(ordinal + 1) % 3]
		fun whatICrush() = values()[(ordinal + 2) % 3]
	}
}
