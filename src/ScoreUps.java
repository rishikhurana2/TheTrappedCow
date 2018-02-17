import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		i.drawImage(GamePanel.scoreUpImg, x, y, width + 100, height + 100, null);
		scoreUpsCollisionBox.setBounds(x + 16, y + 25, width + 25, height + 25);
//		Graphics2D g2d = (Graphics2D) i;
//		g2d.setColor(Color.red);
//		g2d.draw(scoreUpsCollisionBox);
	}
	void update() {
		x = x - powerUpsSpeed;
	}
}
