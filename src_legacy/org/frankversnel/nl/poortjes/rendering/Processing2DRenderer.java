package org.frankversnel.nl.poortjes.rendering;

import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.component.Color;
import org.frankversnel.nl.poortjes.resourceloading.ProcessingShapeLoader;

import processing.core.PGraphics;

public class Processing2DRenderer implements Renderer {
	private PGraphics graphics;
	
	private ProcessingShapeLoader shapeLoader;
	
	private int backgroundColor;

	public Processing2DRenderer(PGraphics graphics,
			ProcessingShapeLoader shapeLoader, int backgroundColor) {
		this.graphics = graphics;
		
		this.shapeLoader = shapeLoader;
		
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void drawRectangle(Transform transform, Color color) {
		fill(color);

		graphics.setMatrix(transform.getProcessingMatrix());
		graphics.pushMatrix();
		graphics.rect(0, 0, transform.getWidth(), transform.getHeight());
		graphics.popMatrix();
	}

	@Override
	public void drawCircle(Transform transform, Color color) {
		fill(color);

		graphics.setMatrix(transform.getProcessingMatrix());
		graphics.pushMatrix();
		graphics.ellipse(0, 0, transform.getWidth(), transform.getHeight());
		graphics.popMatrix();
	}

	private void fill(Color color) {
		graphics.fill(color.r(), color.g(), color.b());
	}

	@Override
	public void drawShape(Transform transform, String shapeId) {
		graphics.setMatrix(transform.getProcessingMatrix());
		graphics.pushMatrix();
		graphics.shape(shapeLoader.getResource(shapeId), 0, 0,
				transform.getWidth(), transform.getHeight());
		graphics.popMatrix();
	}

	@Override
	public void clearScreen() {
		graphics.background(backgroundColor);
	}

}