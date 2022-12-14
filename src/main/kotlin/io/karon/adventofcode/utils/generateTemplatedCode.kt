package io.karon.adventofcode.utils

fun main() {
	generateDayStarClasses()
	generateMainClass()
}

fun generateDayStarClasses(year: Int? = null) {
	EventDayFinder.findExistingEventDays().forEach { eventDay ->
		if (year == null || eventDay.year == year) {
			(1..2).forEach { star ->
				if (eventFileNotExists(eventDay, star)) {
					println("Creating event file for ${eventDay.year}-12-${eventDay.day}")
					writeToEventFile(
						eventDay,
						star,
						"""
						package io.karon.adventofcode.year${eventDay.year}
						
						import io.karon.adventofcode.utils.EventDay
						import io.karon.adventofcode.utils.Star
						import io.karon.adventofcode.utils.getAllLinesFromInput
						
						class Day${eventDay.day}Star$star : Star {
							override val expectedResult: Int get() = super.expectedResult
							val input = getAllLinesFromInput(EventDay(${eventDay.year}, ${eventDay.day}))
						
							override fun getResult(): Any {
								return super.getResult()
							}
						}
				""".trimIndent()
					)
				}
			}
		}
	}
}

fun generateDayStarTestClasses(year: Int? = null) {
	EventDayFinder.findExistingEventDays().forEach { eventDay ->
		if (year == null || eventDay.year == year) {
			(1..2).forEach { star ->
				if (testEventFileNotExists(eventDay, star)) {
					println("Creating test event file for ${eventDay.year}-12-${eventDay.day}")
					writeToTestEventFile(
						eventDay,
						star,
						"""
						package io.karon.adventofcode.year${eventDay.year}
						
						import org.junit.jupiter.api.Test
						import kotlin.test.assertEquals

						internal class Day${eventDay.day}Star${star}Test {
							private val testInput = ""${'"'}
								
							""${'"'}.trimIndent()
								.split("\n")
							@Test
							fun `test input`() {
								val actual = testInput
								//assertEquals(0, actual)
							}
						}
				""".trimIndent()
					)
				}
			}
		}
	}
}

fun generateMainClass(year: Int? = null) {
	EventDayFinder.findExistingEventDays().fold(mutableMapOf<Int, Int>()) { map, eventDay ->
		if (year == null || eventDay.year == year) {
			map.compute(eventDay.year) { _, oldValue ->
				if (oldValue == null) {
					eventDay.day
				} else {
					maxOf(eventDay.day, oldValue)
				}
			}
		}
		map
	}.forEach { (year, day) ->
		writeToMainFile(
			year,
			"""
package io.karon.adventofcode.year$year

import io.karon.adventofcode.utils.Day
import io.karon.adventofcode.utils.printResult

fun main() {
${
				(1..day).joinToString(System.lineSeparator()) {
					"    Day($it, Day${it}Star1(), Day${it}Star2()).printResult()"
				}
			}
}
		""".trimIndent()
		)
	}
}
