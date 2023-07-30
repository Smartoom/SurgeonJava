import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	public static int boundsW, boundsH;

	private ArrayList<Item> items = new ArrayList<>();
	private WorkTable workTable;
	public static int trashSpawned = 0;

	private boolean capturing = false;
	private Item capturedItem;
	private double capture_xOffset = 0;
	private double capture_yOffset = 0;

	private boolean showHint = false;
	private Item hoveringItem;
	
	private int minimumTrash = 2;//out of 100

	// ################debug
	private boolean showCollisionBox = true;

	public GamePanel() {
		new GameMenus();
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

		while (trashSpawned < minimumTrash) {
			for (int i = 0; i < items.size(); i++) {
				if (!items.get(i).getClass().isAssignableFrom(Trash.class)) {
					Random rand = new Random();
					if (rand.nextInt(101) > 75) {
						trashSpawned++;
						Trash bicycle = new Trash(items.get(i).x + rand.nextInt(20) - 10, items.get(i).y + rand.nextInt(30), 0.2, 0.2);
						System.out.println("spawned trash on " + ((Organ) items.get(i)).type);
						items.add(i, bicycle);
					}
				}
			}
		}

		workTable = new WorkTable();

		addMouseListener(this);
		addMouseMotionListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		boundsW = getSize().width;
		boundsH = getSize().height;

		if (GameMenus.instance.won) {
			GameMenus.instance.DrawWin(g);
			return;
		}

		// background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());

		workTable.meatBackRenderer.Draw(g);

		workTable.skinRenderer.Draw(g);
		workTable.DrawBounds(g);
		workTable.iceBagRenderer.Draw(g);

		for (Item item : items) {
			item.spriteRenderer.Draw(g, item.x, item.y);
		}
		if (showHint) {
			if (showCollisionBox)
				hoveringItem.DrawBounds(g);

			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.setColor(Color.gray);
			g.fillRect((int) hoveringItem.x + 50, (int) hoveringItem.y, 90, 30);
			g.setColor(Color.black);
			g.drawString(hoveringItem.hintName, (int) hoveringItem.x + 55, (int) hoveringItem.y + 20);
		}

		workTable.DrawScore(g);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (capturing) {
			capturedItem.x = e.getX() - capture_xOffset;
			capturedItem.y = e.getY() - capture_yOffset;

			repaint();
			// if ((Organ) capturedItem == capturedItem) { System.out.println( "dragging " +
			// ((Organ) capturedItem).type + " x: " + capturedItem.x + " y: " +
			// capturedItem.y); } else { System.out.println("dragging item"); }
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		ArrayList<Item> revItems = new ArrayList<>(items);
		Collections.reverse(revItems);

		for (Item item : revItems) {
			if (mouseX > item.x && (item.x + item.w) > mouseX) {
				if (mouseY > item.y && (item.y + item.h) > mouseY) {

					showHint = true;
					hoveringItem = item;
					repaint();
					return;
				} else
					showHint = false;
			} else
				showHint = false;
		}
		if (!showHint) {
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		ArrayList<Item> revItems = new ArrayList<>(items);
		Collections.reverse(revItems);

		System.out.println("clicked: " + mouseX + ", " + mouseY);
		for (Item item : revItems) {
			if (mouseX > item.x && (item.x + item.w) > mouseX) {
				if (mouseY > item.y && (item.y + item.h) > mouseY) {
					System.out.println("captured " + item);

					showHint = false;
					pushItemUp(item);
					repaint();
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
		// System.out.println("was: " + items);
		items.remove(item);
		items.add(item);
		// System.out.println("is: " + items);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (capturing) {
			System.out.println("released item");

			workTable.Place(capturedItem);
			repaint();
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
