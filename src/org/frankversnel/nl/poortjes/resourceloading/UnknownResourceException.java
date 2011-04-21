package org.frankversnel.nl.poortjes.resourceloading;

public class UnknownResourceException extends RuntimeException {
	private static final long serialVersionUID = 868626041323287071L;

	public UnknownResourceException(String message) {
		super(message);
	}

	public UnknownResourceException(String message, Exception e) {
		super(message, e);
	}

}
