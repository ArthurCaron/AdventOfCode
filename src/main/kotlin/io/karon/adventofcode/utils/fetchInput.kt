package io.karon.adventofcode.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun main() {
	writeAllInputToFile()
}

suspend fun writeAllInputToFile(year: Int? = null) {
	EventDayFinder.findExistingEventDays().forEach {
		if (year == null || it.year == year) {
			if (resourceFileNotExists(it)) {
				println("Fetching input for ${it.year}-12-${it.day}")
				writeToResourceFile(it, fetchInput(it))
			}
		}
	}
}

private suspend fun fetchInput(eventDay: EventDay): String {
	val session = PropertyReader.getProperty("session")
	HttpClient().use { client ->
		val response: HttpResponse = client.get("https://adventofcode.com/${eventDay.year}/day/${eventDay.day}/input") {
			headers {
				append(HttpHeaders.Cookie, "session=$session")
			}
		}
		return response.body<String>().trimEnd()
	}
}
