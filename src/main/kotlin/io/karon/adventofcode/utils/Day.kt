package io.karon.adventofcode.utils

class Day(
	val day: Int,
	val star1: Star,
	val star2: Star
)

fun Day.printResult() {
	println("Day ${day}: expected/actual | Star 1: ${star1.expectedResult} / ${star1.getResult()} | Star 2: ${star2.expectedResult} / ${star2.getResult()}")
}
