package org.frankversnel.poortjes

import org.frankversnel.poortjes._

trait Color extends Component {
	val color: (Int, Int, Int)

	def r = color._1
	def g = color._2
	def b = color._3
}
object Color {
	val red = (255, 0, 0)
	val green = (0, 255, 0)
	val blue = (0, 0, 255)
}
