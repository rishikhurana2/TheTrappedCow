import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUps {
	int x;
	int y;
	int width;
	int height;
	Rectangle powerUpsCollision;
	Cow cow;
	PowerUps(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		powerUpsCollision = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
	}
	void update() {
		
	}
}
