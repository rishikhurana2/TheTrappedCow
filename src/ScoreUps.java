import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScoreUps {
	int x;
	int y;
	int width;
	int height;
	int powerUpsSpeed = 5;
	Rectangle powerUpsCollision;
	Cow cow;
	ScoreUps(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		powerUpsCollision = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
		i.setColor(Color.red);
		i.fillRect(x, y, width, height);
		powerUpsCollision.setBounds(x, y, width, height);
	}
	void update() {
		x = x - powerUpsSpeed;
	}
}
