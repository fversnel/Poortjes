package org.frankversnel.poortjes.util

import java.util.Properties

object GameConfiguration {
	private val configuration = new Properties
	configuration.load(getClass.getClassLoader.getResourceAsStream("configuration.properties"))

	def isDebugEnabled = {
		isEnabled("debug")
	}

	def isEnabled(name: String) = {
		getProperty(name).equals("true")
	}

	def getProperty(name: String): String = {
		Option(configuration.getProperty(name)).getOrElse("")
	}
}
