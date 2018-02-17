import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemies {
	int x;
	int y;
	int width;
	int height;
	double speed = 3.5;
	Rectangle enemiesCollisionBox;
	Enemies(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		enemiesCollisionBox = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
		i.setColor(Color.yellow);
		i.fillRect(x, y, width, height);
		enemiesCollisionBox.setBounds(x, y, width, height);
	}
	void update() {
		y = y + (int) speed;
	}
}
