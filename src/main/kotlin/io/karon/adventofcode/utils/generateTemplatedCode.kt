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
						
						import io.karon.adventofcode.utils.Star
						
						class Day${eventDay.day}Star$star : Star {
							override val expectedResult: Int get() = super.expectedResult
						
							override fun getResult(): Int {
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
