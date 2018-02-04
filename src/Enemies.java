import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemies {
	int x;
	int y;
	int width;
	int height;
	int speed = 5;
	Rectangle enemiesCollisionBox;
	Enemies(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		enemiesCollisionBox = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
		i.setColor(Color.black);
		i.fillRect(x, y, width, height);
		enemiesCollisionBox.setBounds(x, y, width, height);
	}
	void update() {
		y = y + speed;
	}
}
