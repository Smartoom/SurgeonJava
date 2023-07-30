import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Trash extends Item {
	private Image bicycle = new ImageIcon("bicycle.png").getImage();
	private Image oppenheimerDisc = new ImageIcon("oppenheimerDVDDisc.png").getImage();
	private Image tumor = new ImageIcon("tumor.png").getImage();
	private Image surgicalKnife = new ImageIcon("surgicalKnife.png").getImage();

	public Trash(double _x, double _y) {
		super(_x, _y);
		ChooseImage();
	}

	private void ChooseImage() {
		Random rand = new Random();
		int randomInt = rand.nextInt(101);
		System.out.println(randomInt);

		// edit chances
		if (randomInt <= 50) {
			SetImage(bicycle);
			hintName = "bicycle";
		} else if (randomInt <= 80) {
			wMult = 0.5;
			hMult = 0.5;
			SetImage(tumor);
			hintName = "tumor";
		} else if (randomInt <= 90) {
			wMult = 0.7;
			hMult = 0.7;
			SetImage(surgicalKnife);
			hintName = "surgical knife";
		} else {
			SetImage(oppenheimerDisc);
			hintName = "a disc";
		}
	}

	public Trash(double _x, double _y, double _wMult, double _hMult) {
		super(_x, _y, _wMult, _hMult);
		ChooseImage();
	}

}
