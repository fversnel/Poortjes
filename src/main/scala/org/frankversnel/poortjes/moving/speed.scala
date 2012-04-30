package org.frankversnel.poortjes.moving

import org.newdawn.slick.geom.Vector2f

import org.frankversnel.poortjes._

trait Speed extends Component {
	val distanceInMs: Float
	val rotationInMs: Float

	private var _rotationSpeed = 0f
	def rotationSpeed = _rotationSpeed * rotationInMs
	def rotationSpeed_= (value: Float): Unit = _rotationSpeed = value

	// Compensate for case: 'diagonal is faster than straight'
	private var _moveSpeed = new Vector2f(0, 0)
	def moveSpeed_= (value: Tuple2[Float, Float]): Unit = _moveSpeed =
			new Vector2f(value._1, value._2)
	def moveSpeed = new Vector2f(_moveSpeed.getX * distanceInMs, _moveSpeed.getY * distanceInMs)

	def distanceSpeed = 0f
}

