import java.awt.Graphics;
import java.awt.Image;

public class SpriteRenderer {
	public double x, y;
	public double w = 1, h = 1;
	public Image image;
	//public SpriteRenderer[] instances;

	public void Draw(Graphics g, double _x, double _y) {
		g.drawImage(image, (int) _x, (int) _y, (int) w, (int) h, null);
	}

	public void Draw(Graphics g) {
		g.drawImage(image, (int) x, (int) y, (int) w, (int) h, null);
	}

	public void SetSize(double _w, double _h) {
		w = _w;
		h = _h;
	}
}
