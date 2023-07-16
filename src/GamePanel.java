import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	public static int boundsW, boundsH;

	private Item[] items;
	private WorkTable workTable;

	private boolean capturing = false;
	private Item capturedItem;
	private double capture_xOffset = 0;
	private double capture_yOffset = 0;

	public GamePanel() {
		Organ intestines = new Organ(175, 220, 1.5, 1.5, Organ.OrganType.Intestines);
		Organ kidney_left = new Organ(170, 235, 1.8, 1.8, Organ.OrganType.Kidney_left);
		Organ kidney_right = new Organ(240, 235, 1.8, 1.8, Organ.OrganType.Kidney_right);
		Organ stomach = new Organ(210, 175, 1.7, 1.7, Organ.OrganType.Stomach);
		Organ liver = new Organ(180, 205, 1.8, 1.8, Organ.OrganType.Liver);
		Organ heart = new Organ(205, 150, 0.5, 0.5, Organ.OrganType.Heart);
		Organ lung_left = new Organ(170, 120, 1.8, 1.8, Organ.OrganType.Lung_left);
		Organ lung_right = new Organ(230, 120, 1.8, 1.8, Organ.OrganType.Lung_right);
		items = new Item[] { intestines, stomach, kidney_left, kidney_right, liver, heart, lung_left, lung_right };

		workTable = new WorkTable();

		addMouseListener(this);
		addMouseMotionListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		boundsW = getSize().width;
		boundsH = getSize().height;

		// background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		workTable.DrawBounds(g);

		workTable.DrawMeatBack(g);
		workTable.DrawSkin(g);

		for (Item item : items) {
			item.Draw(g);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (capturing) {
			capturedItem.x = e.getX() - capture_xOffset;
			capturedItem.y = e.getY() - capture_yOffset;

			repaint();
			if ((Organ) capturedItem == capturedItem) {
				System.out.println(
						"dragging " + ((Organ) capturedItem).type + " x: " + capturedItem.x + " y: " + capturedItem.y);
			} else {
				System.out.println("dragging item");
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		System.out.println("clicked: " + mouseX + ", " + mouseY);
		for (Item item : items) {
			if (mouseX > item.x && (item.x + item.w * item.getWidth()) > mouseX) {
				if (mouseY > item.y && (item.y + item.h * item.getHeight()) > mouseY) {
					if ((Organ) item == item) {
						System.out.println("captured " + ((Organ) item).type);
					} else {
						System.out.println("captured " + item);
					}
					pushItemUp(item); // 7 in array (lung_right)
					capture_xOffset = e.getX() - item.x;
					capture_yOffset = e.getY() - item.y;
					capturing = true;
					capturedItem = item;
					return;
				}
			}

		}
	}

	private void pushItemUp(Item item) {
		int locOfitem = -1;

		for (int i = 0; i < items.length; i++) {
			if (items[i] == item)
				locOfitem = i;
		}
		System.out.println(locOfitem);

		if (locOfitem == -1) {
			System.out.println("nothing found");
			return;
		}
		for (int i = locOfitem; i < items.length; i++) {
			if (i == items.length-1)
				items[i] = item;
			else
				items[i] = items[i + 1];
		}
		System.out.println(items);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (capturing) {
			if ((Organ) capturedItem == capturedItem) {
				System.out.println("released " + ((Organ) capturedItem).type);
			} else {
				System.out.println("released item");
			}
			workTable.Place(capturedItem);
			capturing = false;
			capturedItem = null;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
