package io.karon.adventofcode.utils

fun eventFileNotExists(eventDay: EventDay, star: Int) = !getEventFile(eventDay, star).exists()

fun testEventFileNotExists(eventDay: EventDay, star: Int) = !getTestEventFile(eventDay, star).exists()

fun resourceFileNotExists(eventDay: EventDay) = !getResourceFile(eventDay).exists()

fun writeToMainFile(year: Int, content: String) {
	val parentFolder = getEventParentFolder(year)
	parentFolder.mkdirs()
	val file = getMainFile(year)
	file.writeText(content)
}

fun writeToEventFile(eventDay: EventDay, star: Int, content: String) {
	val parentFolder = getEventParentFolder(eventDay.year)
	parentFolder.mkdirs()
	val file = getEventFile(eventDay, star)
	file.writeText(content)
}

fun writeToTestEventFile(eventDay: EventDay, star: Int, content: String) {
	val parentFolder = getTestEventParentFolder(eventDay.year)
	parentFolder.mkdirs()
	val file = getTestEventFile(eventDay, star)
	file.writeText(content)
}

fun writeToResourceFile(eventDay: EventDay, content: String) {
	val parentFolder = getResourceParentFolder(eventDay.year)
	parentFolder.mkdirs()
	val file = getResourceFile(eventDay)
	file.writeText(content)
}
