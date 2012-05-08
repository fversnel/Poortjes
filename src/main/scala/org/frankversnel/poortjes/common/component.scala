package org.frankversnel.poortjes

import org.frankversnel.poortjes.util.DeltaTime

trait Component extends GameObject {
	def process(update: Update): Unit = {
		// By default does nothing
	}
}
