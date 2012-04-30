package org.frankversnel.poortjes.resource_loading

import org.newdawn.slick.Image

class SlickImageLoader extends ResourceLoader {
	type T = Image

	protected def loadResource(filePath: String): T = {
		try {
			return new Image(filePath)
		} catch {
			// TODO Put runtime exception into resource not found exception
			case e: RuntimeException =>
					throw new ResourceNotFoundException(filePath)
		}
	}
}
