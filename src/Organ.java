import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Organ extends Item {
	// fields (images)
	private Image brainImg = new ImageIcon("brain.png").getImage();
	private Image heartImg = new ImageIcon("heart.png").getImage();
	private Image intestinesImg = new ImageIcon("intestines.png").getImage();
	private Image kidney_leftImg = new ImageIcon("kidneys_left.png").getImage();
	private Image kidney_rightImg = new ImageIcon("kidneys_right.png").getImage();
	private Image liverImg = new ImageIcon("Liver.png").getImage();
	private Image lung_leftImg = new ImageIcon("lung_left.png").getImage();
	private Image lung_rightImg = new ImageIcon("lung_right.png").getImage();
	private Image stomachImg = new ImageIcon("stomach.png").getImage();

	public enum OrganType {
		Brain, Heart, Intestines, Kidney_left, Kidney_right, Liver, Lung_left, Lung_right, Stomach
	}

	public OrganType type;

	public Organ(double _x, double _y, double _w, double _h, OrganType _type) {
		super(_x, _y, _w, _h);
		type = OrganType.valueOf(_type.name());
		hintName = _type.name();
		SetImage();
	}

	public Organ(double _x, double _y, OrganType _type) {
		super(_x, _y);
		type = OrganType.valueOf(_type.name());
		hintName = _type.name();
		SetImage();
	}

	public void Draw(Graphics g) {
		g.drawImage(selectedImg, (int) x, (int) y, (int) (w * getWidth()), (int) (h * getHeight()), null);
	}

	private void SetImage() {
		switch (type) {
		case Brain:
			selectedImg = brainImg;
			break;

		case Heart:
			selectedImg = heartImg;
			break;

		case Intestines:
			selectedImg = intestinesImg;
			break;

		case Kidney_left:
			selectedImg = kidney_leftImg;
			break;

		case Kidney_right:
			selectedImg = kidney_rightImg;
			break;

		case Liver:
			selectedImg = liverImg;
			break;

		case Lung_left:
			selectedImg = lung_leftImg;
			break;

		case Lung_right:
			selectedImg = lung_rightImg;
			break;

		case Stomach:
			selectedImg = stomachImg;
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

}
