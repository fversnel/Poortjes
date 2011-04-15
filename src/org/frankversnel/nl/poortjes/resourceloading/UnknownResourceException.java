package org.frankversnel.nl.poortjes.resourceloading;

public class UnknownResourceException extends RuntimeException {

	public UnknownResourceException(String filePath) {
		super("Resource at " + filePath + " was not found or not loaded.");
	}

}
