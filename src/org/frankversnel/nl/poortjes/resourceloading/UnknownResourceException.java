package org.frankversnel.nl.poortjes.resourceloading;

public class UnknownResourceException extends RuntimeException {
	private static final long serialVersionUID = 868626041323287071L;

	public UnknownResourceException(String filePath) {
		super("Resource at " + filePath + " was not found or not loaded.");
	}

}
