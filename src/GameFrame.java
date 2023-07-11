import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Surgeon");
		frame.setBounds(550, 100, 500, 400);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new GamePanel());
		frame.setVisible(true);

	}

}
