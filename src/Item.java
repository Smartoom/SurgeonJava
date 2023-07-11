import java.awt.Graphics;

public class Item {
	public double x, y;
	public double w, h;

	public Item (double _x,double _y,double _w,double _h) {
		x = _x;
		y = _y;
		w =_w;
		h =_h;
	}

	public void Draw(Graphics g) {
		g.drawRect((int) x, (int) y, (int) w, (int) h);
	}
}
