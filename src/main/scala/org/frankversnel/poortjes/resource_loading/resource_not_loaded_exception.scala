package org.frankversnel.poortjes.resource_loading

case class ResourceNotLoadedException(id: ResourceId) extends 
		Exception("Resource with identifier '" + id + "' could not be loaded.")
