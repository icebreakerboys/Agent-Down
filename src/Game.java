
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;

public class Game extends Canvas implements Runnable{

	private Thread thread;
	public static boolean running = false;

	private static Random r = new Random();

	public static Handler handler;
	public static Player player;
	private HUD hud;
	private Menu menu;
	private Background background;

	private static File musicPath;
	private static int secondsRunning = 0;

	public static STATE state = STATE.PlayScreen;
	public enum STATE {
		PlayScreen(),
		PauseMenu(),
		StartMenu(),
		EndMenu(),
		OptionsMenu,
		HelpMenu()
	}

	public Game()  {
		this.setFocusable(true);

		new Window(this);

		handler = new Handler();
		hud = new HUD();
		player = new Player(Window.WIDTH / 2 - 16, 100, 32, 32, Color.gray);
		menu = new Menu();
		background = new Background();

		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());

		musicPath = new File("Possible Song 1.wav");
		musicPath = new File("Theme.wav");
		playMusic(musicPath);

		Timer timer = new Timer();
		TimerTask updateStage = new TimerTask() {
			@Override
			public void run() {
				playRound(secondsRunning);
				secondsRunning++;
			}
		};
		timer.scheduleAtFixedRate(updateStage, 0, 1000);

	}

	private static void playRound(int secondsRunning) {
		switch (secondsRunning){
			case (0): {
				for (int i = 0; i < 2; i++) {
					//handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT) + HEIGHT, 1));
					handler.addObject(new ShooterEnemy(r.nextInt(WIDTH) - 26, r.nextInt(HEIGHT) + HEIGHT, 2));
				}
				break;
			}
			case (60): {
				handler.makeForDelete();
				for (int i = 0; i < 10; i++) {
					handler.addObject(new Enemy(r.nextInt(WIDTH) - 30, r.nextInt(HEIGHT) + HEIGHT, 7));
					if (i % 3 == 0)
						handler.addObject(new ShooterEnemy(r.nextInt(WIDTH) - 26, r.nextInt(HEIGHT) + HEIGHT, 2));
				}
				break;
			}
			case (120): {
				break;
			}
		}

		if(secondsRunning % 10 == 0) {

			handler.addObject(new HealthPack(r.nextInt(WIDTH) - 16, HEIGHT + r.nextInt(10), ID.HealthPack, 16, 16, Color.green));

			handler.addObject(new Magazine(r.nextInt(WIDTH) - 16, HEIGHT + r.nextInt(10), ID.Magazine, 16, 16, Color.blue));
		}
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

	public void run() {
		requestFocus();
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
				this.tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("HP: " + Player.HEALTH);
				System.out.println("Seconds Ran: " + secondsRunning);

				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		background.tick();
		if(state == STATE.PlayScreen) {
			handler.tick();
			hud.tick();
			Player.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		background.render(g);

		if(state == STATE.PlayScreen || state == STATE.PauseMenu) {
			handler.render(g);
			hud.render(g);
			player.render(g);
			if(state == STATE.PauseMenu)
			menu.pauseRender(g);
		} else {
			menu.render(g);
		}

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

	public static synchronized void playMusic(File file){
		new Thread(() -> {
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				clip.open(audioStream);
				clip.start();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}).start();
	}

	public static void main(String[] args) {
		new Game();
	}

}
