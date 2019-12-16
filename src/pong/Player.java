package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public boolean moveRight, moveLeft;
	public int x, y;
	private int width, height;
	
	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void tick() {
		if (this.moveRight) {
			this.x++;
		} else if (this.moveLeft) {
			this.x--;
		}
		if (this.x + this.width > Game.WIDTH) {
			x = Game.WIDTH-this.width;
		} else if (x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}
