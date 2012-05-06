package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

trait Component extends GameObject {
	def process(deltaTime: DeltaTime): Unit = {
		// By default does nothing
	}
}
