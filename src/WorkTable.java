import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WorkTable {
	private Image body_skinImg = new ImageIcon("Resources/SurgeryBody_Body.png").getImage();
	private Image body_meatImg = new ImageIcon("Resources/SurgeryBody_MeatBack.png").getImage();
	private Image iceBag_Img = new ImageIcon("Resources/IceBag.png").getImage();
	private Image trashBag_Img = new ImageIcon("Resources/TrashBag.png").getImage();
	public SpriteRenderer skinRenderer = new SpriteRenderer();
	public SpriteRenderer meatBackRenderer = new SpriteRenderer();
	public SpriteRenderer iceBagRenderer = new SpriteRenderer();
	public SpriteRenderer trashBagRenderer = new SpriteRenderer();
	private int leftBoundry = iceBag_Img.getWidth(null)-10, rightBoundry = trashBag_Img.getWidth(null)-10;
	private int humanWidthMultiplier = 4, humanHeightMultiplier = 4;

	private int trashCollected = 0;

	public WorkTable() {
		skinRenderer.image = body_skinImg;
		skinRenderer.x = 0;
		skinRenderer.y = -145;
		skinRenderer.SetSize(body_skinImg.getWidth(null) * humanWidthMultiplier, body_skinImg.getHeight(null) * humanHeightMultiplier);

		meatBackRenderer.image = body_meatImg;
		meatBackRenderer.x = 0;
		meatBackRenderer.y = -145;
		meatBackRenderer.SetSize(body_meatImg.getWidth(null) * humanWidthMultiplier, body_meatImg.getHeight(null) * humanHeightMultiplier);

	}

	public void SetUpUIBags() {
		System.out.print("########");
		iceBagRenderer.image = iceBag_Img;
		iceBagRenderer.SetSize(leftBoundry, GamePanel.boundsH);

		trashBagRenderer.image = trashBag_Img;
		trashBagRenderer.x = GamePanel.boundsW - rightBoundry;
		trashBagRenderer.SetSize(rightBoundry, GamePanel.boundsH);
	}

	public void DrawBounds(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, leftBoundry, GamePanel.boundsH);
		g.fillRect(GamePanel.boundsW - rightBoundry, 0, GamePanel.boundsW, GamePanel.boundsH);
	}

	public void DrawScore(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(trashCollected + "/" + GamePanel.trashSpawned + " trash collected", GamePanel.boundsW - 220, 30);
	}

	public void Place(Item item) {
		if (GamePanel.boundsW - rightBoundry < item.x + item.w) {// if placed in right side
			if (item.getClass().isAssignableFrom(Trash.class)) {
				System.out.println("threw away");
				item.x = 5000;
				trashCollected++;
				if (trashCollected == GamePanel.trashSpawned) {
					GameMenus.instance.won = true;
				}
			} else {
				System.out.println("nu uh");
				item.x = GamePanel.boundsW - rightBoundry - item.w;
			}
		} else if (item.x < leftBoundry) {// if placed in left side
			System.out.println("yeeeeah! get that stuff in heeerreee!");
		}
	}
}
