import java.awt.Color;
import java.awt.Graphics;

public class WorkTable {
	private int leftBoundry = 70, rightBoundry = 70;

	public WorkTable() {

	}

	public void Draw(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(0, 0, leftBoundry, GamePanel.boundsH);
		g.drawRect(GamePanel.boundsW-rightBoundry, 0, GamePanel.boundsW, GamePanel.boundsH);
	}
	
	public void Place(Item item) {
		
	}
}
