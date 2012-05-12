package org.frankversnel.poortjes

case class Color private(r: Int, g: Int, b: Int, a: Int) {
	require(inRange(r), "red value was out of range 0-255, value was: " + r)
	require(inRange(g), "green value was out of range 0-255, value was: " + g)
	require(inRange(b), "blue value was out of range 0-255, value was: " + b)
	require(inRange(a), "alpha value was out of range 0-255, value was: " + a)
	private def inRange(value: Int): Boolean = value >= 0 && value <= 255

	def r(value: Int): Color = Color(value, g, b, a)
	def g(value: Int): Color = Color(r, value, b, a)
	def b(value: Int): Color = Color(r, g, value, a)
	def a(value: Int): Color = Color(r, g, b, value)
}
object Color {
	def apply(): Color = Color(0, 0, 0, 255)

	val red = Color().r(255)
	val green = Color().g(255)
	val blue = Color().b(255)
	val white = Color().r(255).g(255).b(255)
	val black = Color().r(0).g(0).b(0)
}
