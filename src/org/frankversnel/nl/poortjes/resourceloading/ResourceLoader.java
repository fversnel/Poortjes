package org.frankversnel.nl.poortjes.resourceloading;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides a standardized way for loading resources that are needed in the
 * game. There is no overhead when adding the same resource multiple times
 * since it will only be added the first time
 * {@link ResourceLoader#addResource(String)} is called.
 */
public abstract class ResourceLoader<T> {
	protected Map<String, T> resources = new HashMap<String, T>();

	/**
	 * Loads the resource into memory, but only if it isn't loaded already.
	 * Use {@link ResourceLoader#getResource(String)} to retrieve it.
	 *
	 * @param filePath the resource's location
	 */
	public void addResource(String filePath) {
		if(!resources.containsKey(filePath)) {
			try {
				resources.put(filePath, loadResource(filePath));
			} catch(FileNotFoundException e) {
				throw new UnknownResourceException(filePath);
			}
		}
	}

	/**
	 * Retrieves the resource, throws a runtime exception if the resource was
	 * never added.
	 *
	 * @param filePath the resource's location
	 */
	public T getResource(String filePath) {
		T resource = resources.get(filePath);

		if(resource == null) {
			throw new UnknownResourceException(filePath);
		}

		return resource;
	}

	/**
	 * Load the resource and return it, the {@link ResourceLoader} class will take
	 * care of storing and retrieving the resource.
	 */
	protected abstract T loadResource(String filePath)
			throws FileNotFoundException;

}
