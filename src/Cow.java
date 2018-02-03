import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cow {
	double gravity = 1.5;
	int speed = 5;
	int jumpSpeed = 5;
	int x;
	int y;
	int width;
	int height;
	int jumpRestriction = 180;
	boolean isFalling = false;
	Cow(int x, int y, int width, int height) {
		this.x = x;
		this.y =  y;
		this.width = width;
		this.height = height;
	}
	void draw(Graphics i) {
		i.setColor(Color.WHITE);
		i.fillRect(x, y, width, height);
	}
	void update() {
		if (GamePanel.moveLeft == true) {
			x = x - speed;
		}
		if (GamePanel.moveRight == true) {
			x = x + speed; 
		}
		if(GamePanel.jumpUp == true) {
			y = y - jumpSpeed;
		}
		if (isFalling) {
			jumpSpeed -= gravity;
		}
	}
	void restrict() {
		if (x > TrappedCow.width - 60) {
			x = TrappedCow.width - 60;
		}
		if (x < 0) {
			x = 0;
		}
		if (y < jumpRestriction) {
			isFalling = true;
		}
		if (y > 250) {
			isFalling = false;
			y = 250;
		}
	}
}
