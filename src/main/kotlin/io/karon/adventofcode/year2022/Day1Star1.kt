package io.karon.adventofcode.year2022

import io.karon.adventofcode.utils.EventDay
import io.karon.adventofcode.utils.Star
import io.karon.adventofcode.utils.getAllLinesFromInput

class Day1Star1 : Star {
	override val expectedResult: Int get() = super.expectedResult

	override fun getResult(): Int {
		val input = getAllLinesFromInput(EventDay(2022, 1))
		return super.getResult()
	}
}
