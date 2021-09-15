package GameObjects.Enemies;

import GameObjects.*;
import Main.*;
import GameObjects.Items.*;
import GameObjects.Player.*;
import java.awt.*;

public class ShooterEnemy extends GameObject {

  private int counter = 20;
  private boolean hasNotSpawnedParachute = true;
  private boolean falling = false;
  private double time = 1;

  public ShooterEnemy(int x, int y, int speed) {
    super(x, y, ID.ShooterEnemy, 48, 48, Color.orange);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
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
      if ((Player.getXPos() < x - 50 && Player.getYPos() < y - 50) || (Player.getXPos() > x + 50 && Player.getYPos() < y - 50) || Player.getYPos() > y + 50) {
        shoot();
      } else counter = 49;
    }
    removeGameObject(true);
    counter++;
  }
  /**
   * returns whether the GameObjects.Enemies.ShooterEnemy is on Screen
   */
  public boolean onScreen() {
    return x > 0 && x < WIDTH - w && y > 0 && y < HEIGHT;
  }
  /**
   * Shoots GameObjects.Enemies.Enemy Bullets at an angle
   */
  public void shoot() {
    int xC = Player.getXPos() - ((int) x + 24);
    int yC = Player.getYPos() - ((int) y + 24);
    double angle = Math.atan2(yC,xC);
    Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.getXPos(), Player.getYPos(), angle, (int) velY, ID.EnemyBullet, color));
  }
  /**
   * Controls velY when GameObjects.ItemsAndMore.Parachute is destroyed
   */
  public void fall() {
    y += velY;
    velY = time;
    if(time <= 8)
      time += .2;
  }
  /**
   * Determines if the GameObjects.GameObject needs to be deleted & then deletes it
   * If Deleted on bottom gives point because it fell
   *
   * @param movesUp Determines if it needs to be deleted while below the screen
   */
  @Override
  public void removeGameObject(boolean movesUp){
    int height = h;
    if(movesUp){
      height = HEIGHT;
    }
    if (x <= -w || x >= WIDTH + w || y <= -h || y >= HEIGHT + height)
      Game.handler.removeObject(this);
      if(y >= HEIGHT + height) {
        HUD.setScore(100);
      }
  }
  public void startFalling() {
    falling = true;
  }
}
