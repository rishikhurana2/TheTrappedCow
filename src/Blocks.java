import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Blocks {
	int x = 820;
	int y;
	int width = 60;
	int height = 60;
	int blockSpeed = 5;
	Rectangle blocksBox;
	Blocks(int y) {
		this.y = y;
		blocksBox = new Rectangle(x,y,width,height);
	}
	void draw(Graphics i) {
		i.setColor(Color.YELLOW);
		i.fillRect(x, y, width, height);
		blocksBox.setBounds(x, y, width, height);
	}
	void update() {
		x = x - blockSpeed;
	}
}
