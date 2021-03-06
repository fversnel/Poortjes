package org.frankversnel.poortjes.resource_loading

/**
 * Provides a standardized way for loading resources that are needed in the
 * game. There is no overhead when adding the same resource multiple times
 * since it will only be added the first time
 * {@link ResourceLoader#addResource(String)} is called.
 */
abstract class ResourceLoader {
	type T

	private var resources = Map[ResourceId, T]()

	/**
	 * Loads the resource into memory, but only if it isn't loaded already.
	 * Use {@link ResourceLoader#getResource(String)} to retrieve it.
	 *
	 * @param filePath the resource's location
	 */
	def addResource(filePath: String): ResourceId = {
		val resourceId = ResourceId(filePath)

		if(!resources.contains(resourceId)) {
			resources += resourceId -> loadResource(filePath)
		}

		resourceId
	}

	/**
	 * Retrieves the resource, throws a runtime exception if the resource was
	 * never added.
	 *
	 * @param filePath the resource's location
	 */
	def getResource(id: ResourceId): T = {
		val resource = resources.get(id);
		resource.getOrElse(throw new ResourceNotLoadedException(id))
	}

	/**
	 * Load the resource and return it, the {@link ResourceLoader} class will take
	 * care of storing and retrieving the resource.
	 */
	protected def loadResource(filePath: String): T

}
