package io.karon.adventofcode.utils

import java.util.*

private const val CONFIG = "local.properties"

object PropertyReader {
	private val properties = Properties()

	init {
		val file = this::class.java.classLoader.getResourceAsStream(CONFIG)
		properties.load(file)
	}

	fun getProperty(key: String): String = properties.getProperty(key)
}
