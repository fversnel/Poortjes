package frankversnel.processing;

public class Color {
	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);

	public final int r;
	public final int g;
	public final int b;

	public Color(final int r, final int g, final int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

}
