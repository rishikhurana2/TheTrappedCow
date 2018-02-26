import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JApplet;

public class Cow {
	double gravity = 1.5;
	int speed = 5;
	int jumpSpeed = 10;
	int x;
	int y;
	int width;
	int height;
	int jumpRestriction = 110;
	Rectangle TopCollisionBox;
	Rectangle BottomBox;
	boolean isFalling = false;
	boolean alive = true;
	Cow(int x, int y, int width, int height) {
		this.x = x;
		this.y =  y;
		this.width = width;
		this.height = height;
		TopCollisionBox = new Rectangle( (int) (x) + 15, y + 25, width - 12, height - 40);
		BottomBox = new Rectangle( (int) (x) + 17, y + 40, width - 15  , height -  40);
	}
	void draw(Graphics i) {
		i.drawImage(GamePanel.cowImg, x, y, width + 15, height + 15, null);
		TopCollisionBox.setBounds( (int) (x) + 15, y + 25, width - 12, height - 40);
		BottomBox.setBounds      ( (int) (x) + 17, y + 40, width - 15  , height -  40);
//		Graphics2D g2d = (Graphics2D) i;
//		g2d.setColor(Color.red);
//		g2d.draw(BottomBox);
//		g2d.setColor(Color.yellow);
//		g2d.draw(TopCollisionBox);
	}
	void update() {
		if(GamePanel.jumpUp) {
			y = y - jumpSpeed;
		}
		if (isFalling) {
			jumpSpeed -= gravity;
		}
		if (GamePanel.moveLeft) {
			x = x - speed;
		}
		if (GamePanel.moveRight) {
			x = x + speed;
		}
	}
	void restrict() {
		if (x < 0) {
			x = 0;
		}
		if (x > TrappedCow.width - 50) {
			x = TrappedCow.width - 50;
		}
		if (y < jumpRestriction) {
			isFalling = true;
			GamePanel.counterForSound++;
		}
		if (y > 250) {
			isFalling = false;
			jumpSpeed = 10;
			GamePanel.jumpUp = false;
			y = 250;
		}
	}
	void playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
	}
}
