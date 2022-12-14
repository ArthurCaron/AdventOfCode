package io.karon.adventofcode.utils

interface Star {
	val expectedResult: Int get() = -1
	fun getResult(): Any = -2
}
