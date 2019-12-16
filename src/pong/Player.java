package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(100, 110, 40, 10);
	}
}
