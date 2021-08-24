import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

  private Thread thread;
  public static boolean running = false;
  private static final Random r = new Random();
  public static Handler handler;
  public static Player player;
  public static Image enemyPic1;
  private final HUD hud;
  private final Menu menu;
  private final Background background;
  private static String musicPath;
  public static int challengeVar = 1;
  public static STATE state = STATE.StartMenu;
  /**
   * Used to determine what phase the game is in
   */
  public enum STATE {
    PlayScreen(),
    PauseMenu(),
    StartMenu(),
    EndMenu(),
    OptionsMenu,
    HelpMenu()
  }

  public static void main(String[] args) {
    new Game();
  }
  /**
   * Game Constructor that
   * 1. Prepares all game objects
   * 2. Starts the Game Window which runs the start method
   * 3. Sets up a mouse and key listener
   * 4. Plays Music
   * 5. Starts the Spawner method in a timer
   */
  public Game() {
    this.setFocusable(true);
    handler = new Handler();
    hud = new HUD();
    player = new Player(Window.WIDTH / 2 - 16, 100);
    menu = new Menu();
    background = new Background();
    enemyPic1 = new ImageIcon(getClass().getClassLoader().getResource("images/Badguy1.png")).getImage();
    new Window(this);
    this.addKeyListener(new KeyInput());
    this.addMouseListener(new MouseInput());
    //musicPath = ("sounds/Possible Song 1.wav");
    musicPath = ("sounds/Theme.wav");
    playMusic(musicPath);

    //FIXME need to optimize this thread with the thread
    // that holds back on transitions between game phases
    // Potentially make a new class
    Timer timer = new Timer();
    TimerTask updateStage = new TimerTask() {
      int timeRunning = 1;
      @Override
      public void run() {
        if(Game.state == STATE.PlayScreen){
          Spawner(timeRunning);
          timeRunning++;
        }
      }
    };
    timer.scheduleAtFixedRate(updateStage, 0, 100);
  }
  /**
   * Spawns enemies and items every so often &
   * Increases the challenge of the game depending
   * on how much time has passed
   *
   * @param timeRunning time that the game has been played
   */
  private static void Spawner(int timeRunning) {
    int randomVar = r.nextInt(7);
    int timeDelay = 11 - challengeVar;
    if(timeDelay <= 1)
      timeDelay = 1;
    if(timeRunning % timeDelay == 0){
      if(randomVar <= 2){
        handler.addObject(new ShooterEnemy(r.nextInt(11) * 48 + 48, r.nextInt(Window.HEIGHT) + Window.HEIGHT, challengeVar, enemyPic1, enemyPic1));
      } else if(randomVar <= 4){
        handler.addObject(new Enemy(r.nextInt(33) * 16 + 48, r.nextInt(Window.HEIGHT) + Window.HEIGHT, challengeVar));
      } else {
        if(Player.hasShockHacker && randomVar == 5) {
          handler.addObject(new ShockEnemy(r.nextInt(2) * 576, r.nextInt(Window.HEIGHT) + Window.HEIGHT, challengeVar, ID.HackedShockEnemy));
        }
        handler.addObject(new ShockEnemy(r.nextInt(2) * 576, r.nextInt(Window.HEIGHT) + Window.HEIGHT, challengeVar, ID.ShockEnemy));
      }
    }
    if (timeRunning % 100  == 0) {
      handler.addObject(new HealthPack(r.nextInt(33)* 16 + 48, Window.HEIGHT + r.nextInt(200), 16, 16));
      handler.addObject(new Magazine(r.nextInt(33)* 16 + 48, Window.HEIGHT + r.nextInt(200), 16, 16));
    }
    if(timeRunning % 1200 == 0){
      handler.addObject(new BossEnemy(challengeVar));
      challengeVar++;
    }
  }
  /**
   * Starts the Game loop
   */
  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }
  /**
   * Stops the game loop
   */
  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Game Loop
   * Aims for 80 frames per second
   * with a consistent 60 ticks per second
   */
  public void run() {
    requestFocus();
    long lastTime = System.nanoTime();
    double TARGET_TICK = 60.0;
    double OPTIMAL_TIME_TICKS = 1000000000 / TARGET_TICK;
    double delta = 0;
    final int TARGET_FPS = 60;
    final long OPTIMAL_TIME_FRAMES = 1000000000 / TARGET_FPS;
    long lastFpsTime = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    int ticks = 0;
    while (running) {
      long now = System.nanoTime();
      long updateLength = now - lastTime;
      delta += (now - lastTime) / OPTIMAL_TIME_TICKS;
      lastTime = now;
      lastFpsTime += updateLength;
      if (lastFpsTime >= 1000000000) {
        lastFpsTime = 0;
      }
      while (delta >= 1) {
        tick();
        ticks++;
        delta--;
      }
      render();
      frames++;
      try {
        long x = (lastTime - System.nanoTime() + OPTIMAL_TIME_FRAMES) / 1000000;
        Thread.sleep(x);
      } catch (Exception e) {
      }
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        //System.out.println("FPS: " + frames);
        //System.out.println("Ticks: " + ticks);
        //System.out.println("HP: " + Player.HEALTH);
        frames = 0;
        ticks = 0;
      }
    }
    stop();
  }
  /**
   * Updates all of objects
   * Runs 60 times per second
   * Usually controls movement and collision
   */
  private void tick() {
    background.tick();
    if (state == STATE.PlayScreen) {
      handler.tick();
      hud.tick();
      Player.tick();
    } else {
      menu.tick();
    }
  }
  /**
   * Renders all of the objects
   * Runs about 80 times per second
   */
  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
    background.render(g);
    if (state == STATE.PlayScreen || state == STATE.PauseMenu || state == STATE.EndMenu) {
      handler.render(g);
      if(state == STATE.PlayScreen)
        hud.render(g);
      player.render(g);
    }
    if(state != STATE.PlayScreen)
      menu.render(g);
    g.dispose();
    bs.show();
  }
  /**
   * keeps a variable within bounds
   * Used in tick method for HUD and Player classes
   *
   * @param var initial var
   * @param min bound
   * @param max bound
   * @return the bound back
   */
  public static double clamp(double var, double min, double max) {
    if (var >= max)
      return max;
    else return Math.max(var, min);
  }
  /**
   * Plays songs on repeat
   *
   * @param file the file of the song
   */

  //FIXME needs to be put in separate class
  // to be able to change the song and play song effects (another separate class)
  public synchronized void playMusic(String file) {
    new Thread(() -> {
      try {
        Clip clip = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(file));
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }).start();
  }
}
