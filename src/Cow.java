import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

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
		TopCollisionBox = new Rectangle(x,y,width,height - 7);
		BottomBox = new Rectangle((int)(x), y + 50, width, height - 43);
	}
	void draw(Graphics i) {
		i.setColor(Color.WHITE);
		i.fillRect(x, y, width, height);
		TopCollisionBox.setBounds(x,y,width,height - 7);
		BottomBox.setBounds((int) (x), y + 50, width, height - 43);
//		Graphics2D g2d = (Graphics2D) i;
//		g2d.setColor(Color.yellow);
//		g2d.draw(BottomBox);
//		g2d.setColor(Color.red);
//		g2d.draw(TopCollisionBox);
	}
	void update() {
		if(GamePanel.jumpUp) {
			y = y - jumpSpeed;
		}
		if (isFalling) {
			jumpSpeed -= gravity;
		}
	}
	void restrict() {
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
