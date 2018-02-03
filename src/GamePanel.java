import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	Font titleFont;
	Font tellFont;
	Font smallFont;
	Cow cow; 
	final int MENU_STAGE = 0;
	final int GAME_STAGE = 1;
	final int END_STAGE = 2;
	int currentState = MENU_STAGE;
	int offset = 10;
	int blocksY = 240;
	ArrayList <Blocks> blocks = new ArrayList<Blocks>();
	static boolean moveLeft = false;
	static boolean moveRight = false;
	static boolean jumpUp = false;
	long enemyTimer = 0;
	int spawnTimer = 1000;
	GamePanel() {
		cow = new Cow(50,250,50,50);
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		tellFont = new Font("Times New Roman", Font.PLAIN, 24);
		smallFont = new Font("Times New Roman", Font.PLAIN, 12);
	}
	public void startGame() {
		timer.start();
	}
	public void drawMenuStage(Graphics m) {
		m.setColor(Color.BLACK);
		m.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		m.setFont(titleFont);
		m.setColor(Color.WHITE);
		m.drawString("The Trapped Cow", 200, 100);
		m.setColor(Color.BLUE);
		m.setFont(tellFont);
		m.drawString("Press 'P' to Play", 300, 200);
		m.drawString("Press 'I' for Instructions", 275, 300);
	}
	public void drawGameStage(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		g.setColor(Color.GREEN);
		g.fillRect(-10, 300, 850, 30);
		g.setColor(Color.BLACK);
		g.fillRect(-10, 330, 850, 200);
		g.setFont(tellFont);
		g.drawString("Score: ", 680, 25);
		g.setFont(smallFont);
		g.drawString("Press 'P' to go to the end screen", 620, 60);
		cow.draw(g);
		for (Blocks b: blocks) {
			int getRandomBlocksY = new Random().nextInt(200) + 50;
			b.draw(g, getRandomBlocksY);
		}
	}
	public void drawEndStage(Graphics e) {
		e.setColor(Color.magenta);
		e.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		e.setFont(titleFont);
		e.setColor(Color.BLACK);
		e.drawString("Game Over", 275, 100);
	}
	public void updateMenuStage() {
		
	}
	public void updateGameStage() {
		cow.update();
		cow.restrict();
		for (Blocks b: blocks) {
			b.update();
		}
	}
	public void updateEndStage() {
		
	}
	void addBlocksToBlockArray(Blocks b) {
		blocks.add(b);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STAGE) {
			updateMenuStage();
		}
		if(currentState == GAME_STAGE) {
			updateGameStage();
	        if(System.currentTimeMillis() - enemyTimer >= spawnTimer){
        		addBlocksToBlockArray(new Blocks());
        		enemyTimer = System.currentTimeMillis();
	        }
		}
		if(currentState == END_STAGE) {
			updateEndStage();
		}
	}
	@Override
	public void paintComponent(Graphics i) {
		if (currentState == MENU_STAGE) {
			drawMenuStage(i);
		}
		if (currentState == GAME_STAGE) {
			drawGameStage(i);
		}
		if (currentState == END_STAGE) {
			drawEndStage(i);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_P) {
			if(currentState == MENU_STAGE) {
				currentState = GAME_STAGE;
			}
			else if(currentState == GAME_STAGE) {
				currentState = END_STAGE;
			}
			else if(currentState == END_STAGE) {
				currentState = MENU_STAGE;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jumpUp = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		moveRight = false;
		moveLeft = false;
		jumpUp = false;
	}
}
