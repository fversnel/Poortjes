package org.frankversnel.poortjes.resource_loading

import processing.core.PApplet;
import processing.core.PShape;

class ProcessingShapeLoader(private val pApplet: PApplet) extends ResourceLoader {
	type T = PShape

	protected def loadResource(filePath: String) : T = {
		val newShape = pApplet.loadShape(filePath);

		return if(newShape != null) {
			newShape
		} else {
            throw new ResourceNotFoundException(filePath)
		}
	}

}
