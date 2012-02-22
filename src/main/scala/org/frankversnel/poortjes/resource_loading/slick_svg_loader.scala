package org.frankversnel.poortjes.resource_loading

import org.newdawn.slick.svg.Diagram
import org.newdawn.slick.svg.InkscapeLoader

class SlickSvgLoader extends ResourceLoader {
    type T = Diagram

	protected def loadResource(filePath: String) : T = {
	    try {
    		return InkscapeLoader.load(filePath)
    	} catch {
			case e: RuntimeException => throw new ResourceNotFoundException(filePath)
    	}
	}
}
