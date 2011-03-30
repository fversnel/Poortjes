package frankversnel.processing.rendering;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PShape;

public class ProcessingShapeLoader extends ShapeLoader {
	private Map<String, PShape> shapes = new HashMap<String, PShape>();
	
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
			
		String shapeId = generateId();
		
		shapes.put(shapeId, newShape);
		
		return shapeId;
	}
	
	public PShape getShape(String shapeId) {
		return shapes.get(shapeId);
	}

}
