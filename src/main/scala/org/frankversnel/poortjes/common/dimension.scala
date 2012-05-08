package org.frankversnel.poortjes;

trait Dimension extends Component {
	var dimension: DimensionValue
}

case class DimensionValue private(width: Int, height: Int) {
	def width(value: Int): DimensionValue = DimensionValue(value, height)
	def height(value: Int): DimensionValue = DimensionValue(width, value)
}
object DimensionValue {
	def apply(): DimensionValue = DimensionValue(0, 0)
}
