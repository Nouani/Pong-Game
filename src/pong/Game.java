package pong;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.sun.glass.ui.Window;

public class Game extends Canvas implements Runnable{
	JFrame frame;
	
	private static final int WIDTH = 240;
	private static final int HEIGHT = 120;
	private static final int SIZE = 3;
	
	private Thread thread;
	private boolean isRunnable;
	
	public Game() {
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SIZE, Game.HEIGHT * Game.SIZE));
		initFrame();
	}
	
	public void initFrame() {
		this.frame = new JFrame("Ping-Pong");
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	public void start() {
		this.isRunnable = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void stop() {
		
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		double timer = System.currentTimeMillis();
		int frames = 0;
		
		while (this.isRunnable) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

}
