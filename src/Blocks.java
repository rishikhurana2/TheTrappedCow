import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Blocks {
	int x = 700;
	int width = 60;
	int height = 60;
	int blockSpeed = 5;
	ArrayList <Blocks> blocks = new ArrayList<Blocks>();
	Blocks() {
		
	}
	void draw(Graphics i, int y) {
		i.setColor(Color.YELLOW);
		i.fillRect(x, y, width, height);
	}
	void update() {
		x = x - blockSpeed;
	}
}
