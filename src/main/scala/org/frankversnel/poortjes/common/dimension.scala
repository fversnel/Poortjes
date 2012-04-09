package org.frankversnel.poortjes;

trait Dimension extends Component {
	val dimension: (Int, Int)

	def width = dimension._1
	def height = dimension._2
}
