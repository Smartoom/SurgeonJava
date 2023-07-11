import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	public static int boundsW,boundsH;
	
	private Item[] items;
	private WorkTable workTable;
	
	private boolean capturing = false;
	private Item capturedItem;
	private double capture_xOffset = 0;
	private double capture_yOffset = 0;

	public GamePanel() {
		Organ kidney = new Organ(100, 100, 15, 15);
		Organ heart = new Organ(120, 100, 18, 18);
		Organ lung = new Organ(150, 100, 30, 30);
		items = new Item[] { kidney, heart, lung };

		workTable = new WorkTable();
		
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		boundsW = getSize().width;
		boundsH = getSize().height;
		
		//background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		workTable.Draw(g);
		
		g.setColor(Color.black);
		
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
			System.out.println("dragging " + capturedItem + "    x: " + capturedItem.x + "y: " + capturedItem.y);
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
			if (mouseX > item.x && (item.x + item.w) > mouseX) {
				if (mouseY > item.y && (item.y + item.h) > mouseY) {
					System.out.println("captured " + item);
					capture_xOffset = e.getX() - item.x;
					capture_yOffset = e.getY() - item.y;
					capturing = true;
					capturedItem = item;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (capturing) {
			System.out.println("release " + capturedItem);
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
