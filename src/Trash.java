import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Trash extends Item {
	private Image bicycle = new ImageIcon("bicycle.png").getImage();
	// private Image[] images = {bicycle};

	public Trash(double _x, double _y) {
		super(_x, _y);
//		Random rand = new Random();
//		int randomInt = rand.nextInt(1);
		SetImage(bicycle);
		w = .25;
		h = .2;
		hintName = "bicycle";
	}

	public Trash(double _x, double _y, double _wMult, double _hMult) {
		super(_x, _y, _wMult, _hMult);
		SetImage(bicycle);
		hintName = "bicycle";
	}

}
