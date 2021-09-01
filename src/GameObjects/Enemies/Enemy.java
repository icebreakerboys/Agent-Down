package GameObjects.Enemies;

import GameObjects.*;

import java.awt.Color;

public class Enemy extends GameObject {

  public Enemy(int x, int y, int speed) {
    super(x, y, ID.Enemy, 16, 16, Color.red);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
  }

  public void tick() {
    x += velX;
    y += velY;
    collision();
    removeGameObject(true);
  }
}
