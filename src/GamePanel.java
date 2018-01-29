import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	final int MENU_STAGE = 0;
	final int GAME_STAGE = 1;
	final int END_STAGE = 2;
	int currentState = MENU_STAGE;
	Font titleFont;
	Font tellFont;
	GamePanel() {
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		tellFont = new Font("Times New Roman", Font.PLAIN, 24);
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
		m.drawString("Press Enter to Play", 300, 200);
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
		g.drawString("Score: ", 700, 25);
	}
	public void drawEndStage(Graphics e) {
		e.setColor(Color.magenta);
		e.fillRect(0, 0, TrappedCow.width, TrappedCow.height);
		e.setFont(titleFont);
		e.setColor(Color.BLACK);
		e.drawString("Game Over", 275, 100);
		//e.drawString("Your Score was: ", , y);
	}
	public void updateMenuStage() {
		
	}
	public void updateGameStage() {
		
	}
	public void updateEndStage() {
		
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
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
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
