import java.awt.*;

public class ShooterEnemy extends GameObject {

  private int counter = 20;
  private boolean hasNotSpawnedParachute;
  private boolean falling = false;
  private double time = 1;

  public ShooterEnemy(int x, int y, int speed, Image filePath1, Image filePath2) {
    super(x, y, ID.ShooterEnemy, 48, 48, Color.orange);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
    int picSelect = r.nextInt(2);
    if (picSelect == 0) {
      image = filePath1;
    } else {
      image = filePath2;
    }
    hasNotSpawnedParachute = true;
  }

  public void tick() {
    if(hasNotSpawnedParachute) {
      Game.handler.addObject(new Parachute(x, y, velX, velY, this));
      hasNotSpawnedParachute = false;
    }
    x += velX;
    if(falling) {
      fall();
    } else {
      y += velY;
    }
    collision();
    if (onScreen() && counter % 50 == 0) {
      if ((Player.X < x - 50 && Player.Y < y - 50) || (Player.X > x + 50 && Player.Y < y - 50) || Player.Y > y + 50) {
        shoot();
      } else counter = 49;
    }
    removeGameObject(true);
    counter++;
  }
  /**
   * returns whether the ShooterEnemy is on Screen
   */
  public boolean onScreen() {
    return x > 0 && x < Window.WIDTH - w && y > 0 && y < Window.HEIGHT;
  }
  /**
   * Shoots Enemy Bullets at an angle
   */
  public void shoot() {
    int xC = Player.X - ((int) x + 24);
    int yC = Player.Y - ((int) y + 24);
    double angle = Math.atan2(yC,xC);
    Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.X, Player.Y, angle, (int) velY, ID.BulletEnemy, color));
  }
  /**
   * Controls velY when Parachute is destroyed
   */
  public void fall() {
    y += velY;
    velY = time;
    if(time <= 8)
      time += .2;
  }
  public void startFalling() {
    falling = true;
  }
}
