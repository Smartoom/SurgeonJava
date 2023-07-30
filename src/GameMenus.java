import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameMenus {
	public static GameMenus instance;
	public boolean won;
	
	public GameMenus() {
		instance = this;
	}

	public void DrawWin(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, GamePanel.boundsW, GamePanel.boundsH);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("You", GamePanel.boundsW/2-20, GamePanel.boundsH/2-20);
		g.drawString("Won", GamePanel.boundsW/2-20, GamePanel.boundsH/2+20);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.setColor(Color.gray);
		g.drawString("now leave...", GamePanel.boundsW/2-20, GamePanel.boundsH/2+60);
		g.setFont(new Font("Arial", Font.PLAIN, 6));
		g.drawString("and maybe come back once more...", GamePanel.boundsW/2-20, GamePanel.boundsH/2+70);
	}
}
