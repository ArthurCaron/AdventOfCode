package io.karon.adventofcode.utils

import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

object EventDayFinder {
	fun findExistingEventDays(): List<EventDay> {
		val dayStars = mutableListOf<EventDay>()

		val now = ZonedDateTime.now(ZoneId.of("UTC+1"))
		val (finalYear, finalDay) =
			if (now.month == Month.DECEMBER) {
				if (now.hour > 6) {
					now.year to now.dayOfMonth
				} else {
					now.year to now.dayOfMonth.minus(1)
				}
			} else {
				now.year.minus(1) to 25
			}
		(2015..finalYear).forEach { year ->
			if (year == finalYear) {
				(1..finalDay)
			} else {
				(1..25)
			}.forEach { day ->
				dayStars.add(EventDay(year, day))
			}
		}

		return dayStars
	}
}

data class EventDay(
	val year: Int,
	val day: Int
)
