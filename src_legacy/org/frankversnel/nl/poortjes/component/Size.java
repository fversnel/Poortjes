package org.frankversnel.nl.poortjes.component;

public class Size {
	private float width;
	private float height;

	public Size(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public float width() {
		return this.width;
	}

	public void width(float value) {
		this.width = value;
	}

	public float height() {
		return this.height;
	}

	public void height(float value) {
		this.height = value;
	}

}
