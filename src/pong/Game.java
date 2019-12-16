package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	JFrame frame;
	
	private static final int WIDTH = 240;
	private static final int HEIGHT = 120;
	private static final int SCALE = 3;
	
	private Thread thread;
	private boolean isRunnable;
	
	public Player player;
	
	private BufferedImage layer = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		this.player = new Player();
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
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
		this.isRunnable = false;
		try {
			this.thread.join();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = this.layer.getGraphics();
		player.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(this.layer, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		
		bs.show();
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
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.KEY_PRESSED == KeyEvent.VK_RIGHT) {
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
