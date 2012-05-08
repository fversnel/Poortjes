package org.frankversnel.poortjes

trait Color extends Component {
	val color: ColorValue
}

case class ColorValue private(r: Int, g: Int, b: Int, a: Int) {
	require(inRange(r), "red value was out of range 0-255, value was: " + r)
	require(inRange(g), "green value was out of range 0-255, value was: " + g)
	require(inRange(b), "blue value was out of range 0-255, value was: " + b)
	require(inRange(a), "alpha value was out of range 0-255, value was: " + a)
	private def inRange(value: Int): Boolean = value >= 0 && value <= 255

	def r(value: Int): ColorValue = ColorValue(value, g, b, a)
	def g(value: Int): ColorValue = ColorValue(r, value, b, a)
	def b(value: Int): ColorValue = ColorValue(r, g, value, a)
	def a(value: Int): ColorValue = ColorValue(r, g, b, value)
}
object ColorValue {
	def apply(): ColorValue = ColorValue(0, 0, 0, 255)
}
