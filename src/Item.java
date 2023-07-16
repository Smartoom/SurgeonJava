import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Item {
	public double x, y;
	public double w = 1, h = 1;
	protected Image selectedImg = null;

	public Item(double _x, double _y, double _w, double _h) {
		x = _x;
		y = _y;
		w = _w;
		h = _h;
	}

	public Item(double _x, double _y) {
		x = _x;
		y = _y;
	}

	public void Draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int) x, (int) y, (int) w * getWidth(), (int) h * getHeight());
	}

	public int getWidth() {
		return selectedImg.getWidth(null);
	}

	public int getHeight() {
		return selectedImg.getHeight(null);
	}
}
