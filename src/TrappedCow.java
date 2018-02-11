 import java.awt.Dimension;

import javax.swing.JFrame;

public class TrappedCow {
	JFrame gameFrame = new JFrame();
	GamePanel gamePanel = new GamePanel();
	static int height = 500;
	static int width = 800;
	public static void main(String[] args) {
		TrappedCow TC = new TrappedCow();
		TC.setup();
		}
	public void setup() {
		gameFrame.add(gamePanel);
		gameFrame.setPreferredSize(new Dimension(width, height));
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setSize(width, height);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.startGame();
		gameFrame.addKeyListener(gamePanel);
	}
}
