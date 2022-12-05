package io.karon.adventofcode.utils

import java.io.File

fun getEventParentFolder(year: Int) = File("src/main/kotlin/io/karon/adventofcode/year$year")
fun getResourceParentFolder(year: Int) = File("src/main/resources/year$year")
fun getMainFile(year: Int) = getEventParentFolder(year).resolve("Main.kt")
fun getEventFile(eventDay: EventDay, star: Int) = getEventParentFolder(eventDay.year).resolve("Day${eventDay.day}Star$star.kt")
fun getResourceFile(eventDay: EventDay) = getResourceParentFolder(eventDay.year).resolve("Day${eventDay.day}.txt")
