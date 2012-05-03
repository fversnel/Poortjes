package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

trait Component extends GameObject {
	def process: Unit = {}
	def process(deltaTime: DeltaTime): Unit = {
		process
	}
}
