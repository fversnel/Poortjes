package frankversnel.processing.rendering.component;

public class Color {
	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);
	
	private int r;
	private int g;
	private int b;

	public Color(int r, int g, int b) {
		assertColorValue(r);
		assertColorValue(g);
		assertColorValue(b);
		
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int r() {
		return this.r;
	}
	
	public int g() {
		return this.g;
	}
	
	public int b() {
		return this.b;
	}
	
	private void assertColorValue(int colorValue) {
		assert(colorValue >= 0 && colorValue <= 255);
	}

}
