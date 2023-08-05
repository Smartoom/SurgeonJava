import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Item {
	public double x, y;
	public double w, h;
	public double wMult = 1, hMult = 1;
	public String hintName;
	public SpriteRenderer spriteRenderer = new SpriteRenderer();

	public Item(double _x, double _y, double _wMult, double _hMult) {
		x = _x;
		y = _y;
		wMult = _wMult;
		hMult = _hMult;
	}

	public Item(double _x, double _y) {
		x = _x;
		y = _y;
	}

	public void DrawBounds(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int) x, (int) y, (int) w, (int) h);
	}

	public void SetImage(Image _image) {
		spriteRenderer.image = _image;
		w = wMult * spriteRenderer.image.getWidth(null);
		h = hMult * spriteRenderer.image.getHeight(null);
		spriteRenderer.SetSize(w, h);
	}
}
