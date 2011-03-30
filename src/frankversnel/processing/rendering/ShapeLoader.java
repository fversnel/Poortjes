package frankversnel.processing.rendering;

import java.io.FileNotFoundException;
import java.util.UUID;

public abstract class ShapeLoader {
	
	/**
	 * 
	 * @param filePath
	 * @return the shape id
	 */
	public abstract String load(String filePath) 
			throws FileNotFoundException;
	
	protected final String generateId() {
		return UUID.randomUUID().toString();
	}

}
