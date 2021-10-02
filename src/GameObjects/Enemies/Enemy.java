package GameObjects.Enemies;

import GameObjects.*;

import java.awt.Color;

public class Enemy extends GameObject {

  public Enemy(int speed) {
    super(ID.Enemy, Color.red, false);
    sizeNPosVar(true);
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
