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

	private void SetImage() {
		switch (type) {
		case Brain:
			SetImage(brainImg);
			break;

		case Heart:
			SetImage(heartImg);
			break;

		case Intestines:
			SetImage(intestinesImg);
			break;

		case Kidney_left:
			SetImage(kidney_leftImg);
			break;

		case Kidney_right:
			SetImage(kidney_rightImg);
			break;

		case Liver:
			SetImage(liverImg);
			break;

		case Lung_left:
			SetImage(lung_leftImg);
			break;

		case Lung_right:
			SetImage(lung_rightImg);
			break;

		case Stomach:
			SetImage(stomachImg);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

}
