import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		i.drawImage(GamePanel.rocketEnemy, x, y, width + 30, height + 30, null);
		enemiesCollisionBox.setBounds(x + 20, y, width  - 25, height + 7);
//		Graphics2D g2d = (Graphics2D) i;
//		g2d.setColor(Color.red);
//		g2d.draw(enemiesCollisionBox);
	}
	void update() {
		y = y + (int) speed;
	}
}
