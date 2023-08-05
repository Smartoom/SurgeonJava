import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class FirstPatient extends Client {

	public FirstPatient(Image bodySkin, Image bodyMeat) {
		super(bodySkin, bodyMeat);
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
						Trash bicycle = new Trash(items.get(i).x + rand.nextInt(20) - 10, items.get(i).y + rand.nextInt(30), 0.2, 0.2);
						System.out.println("spawned trash on " + ((Organ) items.get(i)).type + " at: " + items.get(i).x + "," + items.get(i).y);
						items.add(i, bicycle);
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
