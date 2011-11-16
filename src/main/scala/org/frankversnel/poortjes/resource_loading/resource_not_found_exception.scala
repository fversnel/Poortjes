package org.frankversnel.poortjes.resource_loading

case class ResourceNotFoundException(filePath: String) extends 
		Exception("Resource on '" + filePath + "' could not be found.")
