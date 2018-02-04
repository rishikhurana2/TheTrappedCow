import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	ArrayList <Blocks> blocks = new ArrayList<Blocks>();
	ArrayList <ScoreUps> su = new ArrayList<ScoreUps>();
	final int MENU_STAGE = 0;
	final int GAME_STAGE = 1;
	final int END_STAGE = 2;
	int currentState = MENU_STAGE;
	int offset = 10;
	int blocksY = 240;
	static boolean jumpUp = false;
	long enemyTimer = 0;
	int spawnTimer = 1250;
	long scoreUpTimer = 0;
	int scoreUpSpawnTimer = 10000;
	int score = 0;
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
		g.drawString("Score: " + score, 650, 25);
		g.setFont(smallFont);
		g.drawString("Press 'P' to go to the end screen", 620, 60);
		g.drawString("If you Jump on blocks, your score will drastically increase", 10, 20);
		cow.draw(g);
		for (ScoreUps s: su) {
			s.draw(g);
		}
		for (Blocks b: blocks) {
			b.draw(g);
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
        if (System.currentTimeMillis() - enemyTimer >= spawnTimer) {
    		addBlocksToBlockArray(new Blocks(new Random().nextInt(135) + 100));
    		enemyTimer = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - scoreUpTimer >= scoreUpSpawnTimer) {
        		addScoreUpsToScoreUpsArray(new ScoreUps(810,new Random().nextInt(135) + 100, 10,10));
        	scoreUpTimer = System.currentTimeMillis();
        }
		for (Blocks b: blocks) {
			b.update();
		}
		for (ScoreUps p: su) {
			p.update();
		}
		for (Blocks b: blocks) {
			if (b.blocksBox.intersects(cow.TopCollisionBox)) {
				cow.alive = false;
			}
			if (cow.BottomBox.intersects(b.blocksBox)) {
				cow.y = b.y - 46;
				score = score + 35;
			}
		}
		if (cow.alive) {
			score++;
		}
		if (!cow.alive) {
			currentState = END_STAGE;
			cow = new Cow(50,250,50,50);
			blocks.clear();
			su.clear();
			score = 0;
		}
	}
	public void updateEndStage() {
		jumpUp = false;
		cow = new Cow(50,250,50,50);
		blocks.clear();
		su.clear();
		score = 0;
	}
	void addBlocksToBlockArray(Blocks b) {
		blocks.add(b);
	}
	void addScoreUpsToScoreUpsArray(ScoreUps s) {
		su.add(s);
	}
	void destroyUnusedObjects() {
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).x < -60) {
				blocks.remove(i);
			}
		}
		for (int i = 0; i < su.size(); i++) {
			if (cow.TopCollisionBox.intersects(su.get(i).powerUpsCollision) 
				|| cow.BottomBox.intersects(su.get(i).powerUpsCollision)) {
				su.remove(i);
				score = score + 100;
			}
		}
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
			destroyUnusedObjects();
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jumpUp = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
