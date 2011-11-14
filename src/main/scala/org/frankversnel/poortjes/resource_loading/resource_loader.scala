package org.frankversnel.poortjes.resource_loading

/**
 * Provides a standardized way for loading resources that are needed in the
 * game. There is no overhead when adding the same resource multiple times
 * since it will only be added the first time
 * {@link ResourceLoader#addResource(String)} is called.
 */
abstract class ResourceLoader[T] {
	private var resources = Map[String, T]()

	/**
	 * Loads the resource into memory, but only if it isn't loaded already.
	 * Use {@link ResourceLoader#getResource(String)} to retrieve it.
	 *
	 * @param filePath the resource's location
	 */
	def addResource(filePath: String) {
		if(!resources.contains(filePath)) {
			resources += filePath -> loadResource(filePath)
		}
	}

	/**
	 * Retrieves the resource, throws a runtime exception if the resource was
	 * never added.
	 *
	 * @param filePath the resource's location
	 */
	def getResource(filePath: String): T = {
		val resource = resources.get(filePath);

		resource match {
			case Some(x) => return x
			case None => throw new Exception // TODO Throw nice exception
		}
	}

	/**
	 * Load the resource and return it, the {@link ResourceLoader} class will take
	 * care of storing and retrieving the resource.
	 */
	protected def loadResource(filePath: String): T

}

