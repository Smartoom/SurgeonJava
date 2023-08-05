import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

public class Client {
	protected WorkTable workTable;

	protected ArrayList<Item> items = new ArrayList<>();

	protected int minimumTrash = 2;// set in construcor in future

	public Client(Image bodySkin, Image bodyMeat) {
		workTable = new WorkTable(bodySkin, bodyMeat);
	}

	public Client(Image bodySkin, Image bodyMeat, int minTrash) {
		workTable = new WorkTable(bodySkin, bodyMeat);
		minimumTrash = minTrash;
	}

	public void Draw(Graphics g, boolean showCollision) {
	}

	public void PushItemUp(Item item) {
		items.remove(item);
		items.add(item);
	}

	public void Place(Item item) {
		workTable.Place(item);
	}

	public WorkTable GetWorkTable() {
		return workTable;
	}

	public Item CheckPoint(double x, double y) {
		ArrayList<Item> revItems = new ArrayList<>(items);
		Collections.reverse(revItems);

		for (Item item : revItems) {
			if (x > item.x && (item.x + item.w) > x) {
				if (y > item.y && (item.y + item.h) > y) {
					return item;
				}
			}
		}
		return null;
	}
}
