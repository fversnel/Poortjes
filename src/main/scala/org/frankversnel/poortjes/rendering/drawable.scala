package org.frankversnel.poortjes.rendering

import org.frankversnel.poortjes._

trait Drawable extends Component {
	var color: Color
	var transform: Transform

	def draw(renderer: Renderer): Unit
}
