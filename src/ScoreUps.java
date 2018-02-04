import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScoreUps {
	int x;
	int y;
	int width;
	int height;
	int powerUpsSpeed = 5;
	Rectangle scoreUpsCollisionBox;
	Cow cow;
	ScoreUps(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		scoreUpsCollisionBox = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
		i.setColor(Color.red);
		i.fillRect(x, y, width, height);
		scoreUpsCollisionBox.setBounds(x, y, width, height);
	}
	void update() {
		x = x - powerUpsSpeed;
	}
}
