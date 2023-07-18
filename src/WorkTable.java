import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WorkTable {
	private int leftBoundry = 70, rightBoundry = 70;
	private int humanWidthMultiplier = 4, humanHeightMultiplier = 4;
	private Image body_skinImg = new ImageIcon("SurgeryBody_Body.png").getImage();
	private Image body_meatImg = new ImageIcon("SurgeryBody_MeatBack.png").getImage();
	public SpriteRenderer skinRenderer = new SpriteRenderer();
	public SpriteRenderer meatBackRenderer = new SpriteRenderer();

	private int trashCollected = 0;

	public WorkTable() {
		skinRenderer.image = body_skinImg;
		skinRenderer.x = 0;
		skinRenderer.y = -145;
		skinRenderer.SetSize(body_skinImg.getWidth(null) * humanWidthMultiplier,
				body_skinImg.getHeight(null) * humanHeightMultiplier);

		meatBackRenderer.image = body_meatImg;
		meatBackRenderer.x = 0;
		meatBackRenderer.y = -145;
		meatBackRenderer.SetSize(body_meatImg.getWidth(null) * humanWidthMultiplier,
				body_meatImg.getHeight(null) * humanHeightMultiplier);
	}

	public void DrawBounds(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, leftBoundry, GamePanel.boundsH);
		g.fillRect(GamePanel.boundsW - rightBoundry, 0, GamePanel.boundsW, GamePanel.boundsH);
	}

	public void DrawScore(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString(trashCollected + "/" + GamePanel.trashSpawned, GamePanel.boundsW - 100, 30);
	}

	public void Place(Item item) {
		if (GamePanel.boundsW - rightBoundry < item.x + item.w) {
			if (item.getClass().isAssignableFrom(Trash.class)) {
				System.out.println("threw away");
				item.x = 5000;
				trashCollected++;
			} else {
				System.out.println("nu uh");
				item.x = GamePanel.boundsW - rightBoundry - item.w;
			}
		}
	}
}
