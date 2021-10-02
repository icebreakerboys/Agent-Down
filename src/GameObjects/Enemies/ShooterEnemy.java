package GameObjects.Enemies;

import GameObjects.*;
import Main.*;
import GameObjects.Items.*;
import GameObjects.Player.*;
import Main.Window;

import java.awt.*;

public class ShooterEnemy extends GameObject {

  private int counter = 20;
  private boolean hasNotSpawnedParachute = true;
  private boolean falling = false;
  private double time = 1;

  public ShooterEnemy(int speed) {
    super(ID.ShooterEnemy, Color.orange, false);
    sizeNPosVar(true);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
  }

  public void tick() {
    if(hasNotSpawnedParachute) {
      Game.handler.addObject(new Parachute(velX, velY, this));
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
    if(y >= Window.HEIGHT * 2) {
      HUD.setScore(100);
    }
    counter++;
  }
  /**
   * returns whether the GameObjects.Enemies.ShooterEnemy is on Screen
   */
  public boolean onScreen() {
    return x > 0 && x < Window.WIDTH - w && y > 0 && y < Window.HEIGHT;
  }
  /**
   * Shoots GameObjects.Enemies.Enemy Bullets at an angle
   */
  public void shoot() {
    int xC = Player.getXPos() - ((int) x + 24);
    int yC = Player.getYPos() - ((int) y + 24);
    double angle = Math.atan2(yC,xC);
    Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.getXPos(), Player.getYPos(), angle, ID.Bullet, color, friendly));
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
  public void startFalling() {
    falling = true;
  }
}
