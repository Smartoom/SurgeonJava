import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	public static int boundsW, boundsH;
	public static GamePanel instance;

	private boolean capturing = false;
	private Item capturedItem;
	private double capture_xOffset = 0;
	private double capture_yOffset = 0;

	private boolean showHint = false;
	private Item hoveringItem;

	private Client patient;

	// ################debug
	private boolean showCollisionBox = false;
	private boolean showHoveringCollisionBox = false;

	public GamePanel() {
		new GameMenus();
		instance = this;
		patient = new FirstPatient();

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
		g.fillRect(0, 0, GamePanel.boundsW, GamePanel.boundsH);

		patient.Draw(g, showCollisionBox);

		if (showHint) {
			if (showHoveringCollisionBox)
				hoveringItem.DrawBounds(g);

			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.setColor(Color.gray);
			g.fillRect((int) hoveringItem.x + 50, (int) hoveringItem.y, 90, 30);
			g.setColor(Color.black);
			g.drawString(hoveringItem.hintName, (int) hoveringItem.x + 55, (int) hoveringItem.y + 20);
		}

	}
	
	public void startNextLevel() {
		if(patient.getClass().isAssignableFrom(FirstPatient.class)) {
		//change later
		patient = new CrewmatePatient();
		}else
			GameMenus.instance.won = true;
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
		hoveringItem = patient.CheckPoint(mouseX, mouseY);
		if (hoveringItem != null) {// found something
			showHint = true;
			repaint();
		} else {
			showHint = false;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		System.out.println("clicked: " + mouseX + ", " + mouseY);

		if (showHint) {
			System.out.println("captured " + hoveringItem);
			patient.PushItemUp(hoveringItem);
			showHint = false;
			capturing = true;
			capturedItem = hoveringItem;
			repaint();
			capture_xOffset = e.getX() - hoveringItem.x;
			capture_yOffset = e.getY() - hoveringItem.y;
			hoveringItem = null;
			return;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (capturing) {
			System.out.println("released item");

			patient.Place(capturedItem);
			repaint();
			capturing = false;
			capturedItem = null;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
