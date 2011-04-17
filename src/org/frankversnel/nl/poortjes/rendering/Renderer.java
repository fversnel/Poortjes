package org.frankversnel.nl.poortjes.rendering;

import org.frankversnel.nl.poortjes.component.Transform;
import org.frankversnel.nl.poortjes.rendering.component.Color;

public interface Renderer {

	public void drawRectangle(Transform transform, Color color);

	public void drawCircle(Transform transform, Color color);

	public void drawShape(Transform transform, String shapeId);

	public void clearScreen();

}
