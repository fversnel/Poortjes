package org.frankversnel.poortjes

import org.frankversnel.poortjes._

trait Color extends Component {
	val color: ColorValue

	def r = color.r
	def g = color.g
	def b = color.b
}

case class ColorValue(r: Int, g: Int, b: Int)
object ColorValue {
	val red = ColorValue(255, 0, 0)
	val green = ColorValue(0, 255, 0)
	val blue = ColorValue(0, 0, 255)
}
