import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Cow {
	double gravity = 1.5;
	int speed = 5;
	int jumpSpeed = 10;
	static int x;
	static int y;
	int width;
	int height;
	int jumpRestriction = 110;
	int screenWidth = TrappedCow.width - 60;
	Rectangle collisionBox;
	boolean isFalling = false;
	boolean alive = true;
	Cow(int x, int y, int width, int height) {
		Cow.x = x;
		Cow.y =  y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x,y,width,height - 10);
	}
	void draw(Graphics i) {
		i.setColor(Color.WHITE);
		i.fillRect(x, y, width, height);
		collisionBox.setBounds(x,y,width,height - 10);
	}
	void update() {
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
			jumpSpeed = 10;
			GamePanel.jumpUp = false;
			y = 250;
		}
	}
}
