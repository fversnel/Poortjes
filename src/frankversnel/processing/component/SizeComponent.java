package frankversnel.processing.component;

public class SizeComponent extends Component {
	private int width;
	private int height;
	
	public SizeComponent(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

}
