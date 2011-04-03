package org.frankversnel.nl.poortjes.resourceloading;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ResourceLoader <T> {
	protected Map<String, T> resources = new HashMap<String, T>();
	
	/**
	 * 
	 * @param filePath
	 * @return the resource id
	 */
	public abstract String load(String filePath) throws FileNotFoundException;
	
	public T getResource(String resourceId) {
		return resources.get(resourceId);
	}
	
	protected String addResource(T resource) {
		String resourceId = generateId();
		resources.put(resourceId, resource);
		return resourceId;
	}
	
	private final String generateId() {
		return UUID.randomUUID().toString();
	}

}
