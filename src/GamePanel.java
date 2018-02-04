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
	ArrayList <Blocks> blocks = new ArrayList<Blocks>();
	ArrayList <ScoreUps> su = new ArrayList<ScoreUps>();
	ArrayList <Enemies> enemies = new ArrayList<Enemies>();
	final int MENU_STAGE = 0;
	final int GAME_STAGE = 1;
	final int END_STAGE = 2;
	final int INSTRUCTION_STAGE = 3;
	int currentState = MENU_STAGE;
	int offset = 10;
	int blocksY = 240;
	static boolean jumpUp = false;
	static boolean moveLeft = false;
	static boolean moveRight = false;
	long blocksTimer = 0;
	int blockSpawnTimer = 1250;
	long scoreUpTimer = 0;
	int scoreUpSpawnTimer = 10000;
	long enemyTimer = 0;
	int enemySpawnTimer = 5000;
	int score = 0;
	GamePanel() {
		cow = new Cow(50,250,50,50);
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		tellFont = new Font("Times New Roman", Font.PLAIN, 24);
		smallFont = new Font("Times New Roman", Font.PLAIN, 14);
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
		g.fillRect(-10, 300, TrappedCow.width + 10, 30);
		g.setColor(Color.BLACK);
		g.fillRect(-10, 330, TrappedCow.width + 10, 175);
		g.setFont(tellFont);
		g.drawString("Score: " + score, 650, 25);
		g.setFont(smallFont);
		g.drawString("Press 'P' to go to the end screen", 620, 60);
		cow.draw(g);
		for (ScoreUps s: su) {
			for (Blocks b: blocks) {
				if (!s.scoreUpsCollisionBox.intersects(b.blocksBox)) {
					s.draw(g);
				}
			}
		}
		for (Blocks b: blocks) {
			b.draw(g);
		}
		for (Enemies e: enemies) {
			e.draw(g);
		}
	}
	public void drawEndStage(Graphics e) {
		e.setColor(Color.magenta);
		e.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		e.setFont(titleFont);
		e.setColor(Color.BLACK);
		e.drawString("Game Over", 275, 50);
		e.setFont(tellFont);
		e.drawString("Your Score was " + score, 310, 200);
		e.drawString("Press 'P' to go back to the menu", 250 , 350);
	}
	public void drawInstructionStage(Graphics i) {
		i.setColor(Color.black);
		i.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		i.setFont(titleFont);
		i.setColor(Color.white);
		i.drawString("Instructions", TrappedCow.width/2 - 125, 50);
		i.setFont(tellFont);
		i.drawString("Controls", 25, 75);
		i.drawLine(25, 80, 110, 80);
		i.drawString("Space = Jump" , 25, 125);
		i.drawString("Description of Items", 25, 175);
		i.drawLine(25, 180, 220, 180);
		i.setFont(smallFont);
		i.drawString("This game is a scroller, and there is blocks coming in from the right. What you need to do is AVOID THOSE BLOCKS", 25, 200);
		i.drawString("Along the way, you will see red squares, and if you get those squares, your score will increase.", 25, 215);
		i.drawString("Your score is displayed on the top right. As you navigate through the cow's infinite run, there will be enemies that will", 25, 230);
		i.drawString("spawn from the sky, and YOU MUST DODGE THEM.", 25,  245);
		i.setFont(tellFont);
		i.drawString("Press 'esc' to go back to the home screen", 25, 470);
	}
	public void updateMenuStage() {
		score = 0;
	}
	public void updateGameStage() {
		cow.update();
		cow.restrict();
        if (System.currentTimeMillis() - blocksTimer >= blockSpawnTimer) {
    			addBlocksToBlockArray(new Blocks(new Random().nextInt(135) + 100));
    			blocksTimer = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - scoreUpTimer >= scoreUpSpawnTimer) {
        		addScoreUpsToScoreUpsArray(new ScoreUps(810, new Random().nextInt(135) + 100, 10, 10));
        		scoreUpTimer = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - enemyTimer >= enemySpawnTimer) {
        		if (score > 20000) {
        			addEnemiesToEnemiesArray(new Enemies(new Random().nextInt(780) + 15, -10, 60,60));
        		}
        		enemyTimer = System.currentTimeMillis();
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
		for (Enemies e : enemies) {
			if (e.enemiesCollisionBox.intersects(cow.TopCollisionBox)||
				e.enemiesCollisionBox.intersects(cow.BottomBox)) {
				cow.alive = false;
			}
				
		}
		for (Enemies e: enemies) {
			e.update();
		}
		if (cow.alive) {
			score++;
		}
		if (!cow.alive) {
			currentState = END_STAGE;
			cow = new Cow(50,250,50,50);
			blocks.clear();
			su.clear();
			enemies.clear();
		}
	}
	public void updateEndStage() {
		jumpUp = false;
		cow = new Cow(50,250,50,50);
		blocks.clear();
		su.clear();
		enemies.clear();
	}
	void addBlocksToBlockArray(Blocks b) {
		blocks.add(b);
	}
	void addScoreUpsToScoreUpsArray(ScoreUps s) {
		su.add(s);
	}
	void addEnemiesToEnemiesArray(Enemies e) {
		enemies.add(e);
	}
	void destroyUnusedObjects() {
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).x < -60) {
				blocks.remove(i);
			}
		}
		for (int i = 0; i < su.size(); i++) {
			if (cow.TopCollisionBox.intersects(su.get(i).scoreUpsCollisionBox) 
				|| cow.BottomBox.intersects(su.get(i).scoreUpsCollisionBox)) {
				su.remove(i);
				score = score + 250;
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).y > 500) {
				enemies.remove(i);
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
		if (currentState == INSTRUCTION_STAGE) {
			drawInstructionStage(i);
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
			} else if (currentState == GAME_STAGE) {
				currentState = END_STAGE;
			} else if (currentState == END_STAGE) {
				currentState = MENU_STAGE;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_I && currentState == MENU_STAGE) {
			currentState = INSTRUCTION_STAGE;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && currentState == INSTRUCTION_STAGE) {
			currentState = MENU_STAGE;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME_STAGE) {
			jumpUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME_STAGE && score > 20000) {
			moveLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME_STAGE && score > 20000) {
			moveRight = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		moveRight = false;
		moveLeft = false;
	}
}
