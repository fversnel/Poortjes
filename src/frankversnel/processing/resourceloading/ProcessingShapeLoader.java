package frankversnel.processing.resourceloading;

import java.io.FileNotFoundException;

import processing.core.PApplet;
import processing.core.PShape;

public class ProcessingShapeLoader extends ResourceLoader<PShape> {
	private PApplet pApplet;
	
	public ProcessingShapeLoader(PApplet pApplet) {
		this.pApplet = pApplet;
	}
	
	/**
	 * @return the shape's id
	 * @throws FileNotFoundException 
	 */
	public String load(String fileName) throws FileNotFoundException {
		PShape newShape = pApplet.loadShape(fileName);
		if(newShape == null) {
			throw new FileNotFoundException();
		}
		
		return addResource(newShape);
	}

}
