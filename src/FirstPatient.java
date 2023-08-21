import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class FirstPatient extends Client {

	private Image bicycle = new ImageIcon("Resources/bicycle.png").getImage();
	private Image oppenheimerDisc = new ImageIcon("Resources/oppenheimerDVDDisc.png").getImage();
	private Image tumor = new ImageIcon("Resources/tumor.png").getImage();
	private Image surgicalKnife = new ImageIcon("Resources/surgicalKnife.png").getImage();
	private Image fish = new ImageIcon("Resources/fish.png").getImage();

	public FirstPatient() {
		super(new ImageIcon("Resources/SurgeryBody_Body.png").getImage(), new ImageIcon("Resources/SurgeryBody_MeatBack.png").getImage());
		workTable.skinRenderer.x = 0;
		workTable.skinRenderer.y = -145;
		workTable.meatBackRenderer.x = 0;
		workTable.meatBackRenderer.y = -145;
		Organ intestines = new Organ(175, 220, 1.5, 1.5, Organ.OrganType.Intestines);
		Organ kidney_left = new Organ(170, 235, 1.8, 1.8, Organ.OrganType.Kidney_left);
		Organ kidney_right = new Organ(240, 235, 1.8, 1.8, Organ.OrganType.Kidney_right);
		Organ stomach = new Organ(210, 175, 1.7, 1.7, Organ.OrganType.Stomach);
		Organ liver = new Organ(180, 205, 1.8, 1.8, Organ.OrganType.Liver);
		Organ heart = new Organ(205, 150, 0.5, 0.5, Organ.OrganType.Heart);
		Organ lung_left = new Organ(170, 120, 1.8, 1.8, Organ.OrganType.Lung_left);
		Organ lung_right = new Organ(230, 120, 1.8, 1.8, Organ.OrganType.Lung_right);
		items.add(intestines);
		items.add(stomach);
		items.add(kidney_left);
		items.add(kidney_right);
		items.add(liver);
		items.add(heart);
		items.add(lung_left);
		items.add(lung_right);

		int trashSpawned = 0;
		while (trashSpawned < minimumTrash) {
			for (int i = 0; i < items.size(); i++) {
				if (!items.get(i).getClass().isAssignableFrom(Trash.class)) {
					Random rand = new Random();
					if (rand.nextInt(101) > 75) {
						trashSpawned++;
						Trash trash = new Trash(items.get(i).x + rand.nextInt(20) - 10, items.get(i).y + rand.nextInt(30), 0.2, 0.2);

						Random randTrashInt = new Random();
						int randomInt = randTrashInt.nextInt(101);
						// edit chances
						if (randomInt <= 20) {
							trash.SetImage(bicycle);
							trash.hintName = "bicycle";
						} else if (randomInt <= 40) {
							trash.wMult = 0.5;
							trash.hMult = 0.5;
							trash.SetImage(fish);
							trash.hintName = "fish";
						} else if (randomInt <= 80) {
							trash.wMult = 0.5;
							trash.hMult = 0.5;
							trash.SetImage(tumor);
							trash.hintName = "tumor";
						} else if (randomInt <= 95) {
							trash.wMult = 0.7;
							trash.hMult = 0.7;
							trash.SetImage(surgicalKnife);
							trash.hintName = "surgical knife";
						} else {
							trash.SetImage(oppenheimerDisc);
							trash.hintName = "a disc";
						}

//						System.out.println("spawned " + trash.hintName);
//						System.out.println("spawned trash on " + ((Organ) items.get(i)).type + " at: " + items.get(i).x + "," + items.get(i).y);
						items.add(i, trash);
					}
				}
			}
		}
		workTable.trashSpawned = trashSpawned;

	}

	@Override
	public void Draw(Graphics g, boolean showCollision) {
		workTable.SetUpUIBags();

		workTable.meatBackRenderer.Draw(g);

		workTable.skinRenderer.Draw(g);
		workTable.DrawBounds(g);
		workTable.iceBagRenderer.Draw(g);
		workTable.trashBagRenderer.Draw(g);

		for (Item item : items) {
			item.spriteRenderer.Draw(g, item.x, item.y);
			if (showCollision)
				item.DrawBounds(g);
		}

		workTable.DrawScore(g);

	}

}
