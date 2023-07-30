import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Trash extends Item {
	private Image bicycle = new ImageIcon("Resources/bicycle.png").getImage();
	private Image oppenheimerDisc = new ImageIcon("Resources/oppenheimerDVDDisc.png").getImage();
	private Image tumor = new ImageIcon("Resources/tumor.png").getImage();
	private Image surgicalKnife = new ImageIcon("Resources/surgicalKnife.png").getImage();
	private Image fish = new ImageIcon("Resources/fish.png").getImage();

	public Trash(double _x, double _y) {
		super(_x, _y);
		ChooseImage();
	}

	private void ChooseImage() {
		Random rand = new Random();
		int randomInt = rand.nextInt(101);

		// edit chances
		if (randomInt <= 20) {
			SetImage(bicycle);
			hintName = "bicycle";
		} else if (randomInt <= 40) {
			wMult = 0.5;
			hMult = 0.5;
			SetImage(fish);
			hintName = "fish";
		} else if (randomInt <= 80) {
			wMult = 0.5;
			hMult = 0.5;
			SetImage(tumor);
			hintName = "tumor";
		} else if (randomInt <= 95) {
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
