import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WorkTable {
	private int leftBoundry = 70, rightBoundry = 70;
	private int humanWidthMultiplier = 4, humanHeightMultiplier = 4;
	private Image body_skinImg = new ImageIcon("SurgeryBody_Body.png").getImage();
	private Image body_meatImg = new ImageIcon("SurgeryBody_MeatBack.png").getImage();

	public WorkTable() {

	}

	public void DrawBounds(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, leftBoundry, GamePanel.boundsH);
		g.fillRect(GamePanel.boundsW - rightBoundry, 0, GamePanel.boundsW, GamePanel.boundsH);
	}

	public void DrawSkin(Graphics g) {
		g.drawImage(body_skinImg, 0, -145, body_skinImg.getWidth(null) * humanWidthMultiplier,
				body_skinImg.getHeight(null) * humanHeightMultiplier, null);
	}

	public void DrawMeatBack(Graphics g) {
		g.drawImage(body_meatImg, 0, -145, body_meatImg.getWidth(null) * humanWidthMultiplier,
				body_meatImg.getHeight(null) * humanHeightMultiplier, null);
	}

	public void Place(Item item) {
		if (GamePanel.boundsW - rightBoundry < item.x) {
			
		}
	}
}
