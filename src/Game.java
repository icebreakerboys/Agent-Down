

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	//variables and objects initialization
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	public static Handler handler;
	public static Player player;
	private HUD hud;
	
	//main game code
	public Game() {
		this.setFocusable(true);
		handler = new Handler();
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());

		hud = new HUD();
		r = new Random();
		
		new Window(WIDTH, HEIGHT, "Sky Dive", this);

		for(int i = 0; i < 1; i++) {
			handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT) + HEIGHT, ID.Enemy, 16, 16, Color.red, 7));
		}
		handler.addObject(new ShooterEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT) + HEIGHT, ID.ShooterEnemy, 26, 26, Color.orange, 2));
		player = new Player(WIDTH / 2 - 16, 100, 32, 32, Color.gray);
		handler.addObject(new GunItem(r.nextInt(WIDTH), r.nextInt(HEIGHT) + HEIGHT, ID.GunItem, 16, 16, Color.blue, 1));
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Necessary Stuff Didn't Write, but do understand
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				//FIXME: the tick method is called several times here
				// in too short of a time, therefore knocking off several hitpoints
				this.tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
		
	//Runs all math Components of the Game
	private void tick(){
		handler.tick();
		hud.tick();
		Player.tick();
	}
	
	//Runs all visual Components of the Game
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		hud.render(g);
		Player.render(g);

		g.dispose();
		bs.show();
	}

	/**
	 * keeps a variable within bounds
	 * Used in tick method for HUD and Player classes
	 * @param var initial var
	 * @param min bound
	 * @param max bound
	 * @return the bound back
	 */
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return max;
		else return Math.max(var, min);
	}
	
	public static void main(String[] args) {
		new Game();	
	}

}
