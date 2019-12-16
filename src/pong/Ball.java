package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 1.7;
	
	public Ball(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		int angle = new Random().nextInt(160 - 45) + 45;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if (this.x + (this.dx*this.speed) + this.width >= Game.WIDTH) {
			dx *= -1;
		} else if (this.x + (this.dx * this.speed) < 0) {
			dx *= -1;
		}
		
		if (this.y >= Game.HEIGHT) {
			System.out.println("Ponto do inimigo!");
			new Game();
			return;
		} else if (this.y < 0) {
			System.out.println("Ponto do Jogador!");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(this.x+(this.dx*this.speed)), 
										 (int)(this.y+(this.dy*this.speed)),
											   this.width, 
											   this.height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if (bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(160 - 45) + 45;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if (this.dy > 0) {
				this.dy *= -1;
			}
		} else if (bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(160 - 45) + 45;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if (this.dy < 0) {
				this.dy *= -1;
			}
		}
		
		this.x += this.dx*this.speed;
		this.y += this.dy*this.speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)this.x, (int)this.y, this.width, this.height);
	}
}
