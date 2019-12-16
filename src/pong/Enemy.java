package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	public double x, y;
	public int width, height;
	
	public Enemy(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		this.x += (Game.ball.x - this.x - 6) * 0.6;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)this.x, (int)this.y, this.width, this.height);
	}
}
