package io.karon.adventofcode.utils

import java.nio.file.Files

fun getAllLinesFromInput(eventDay: EventDay): List<String> {
	return Files.readAllLines(getResourceFile(eventDay).toPath())
}
