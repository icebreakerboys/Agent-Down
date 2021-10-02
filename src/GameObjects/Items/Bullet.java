package GameObjects.Items;

import GameObjects.GameObject;
import GameObjects.ID;
import Main.*;
import GameObjects.Player.Player;

import java.awt.*;

public class Bullet extends GameObject {

  public Bullet(int x, int y, int tX, int tY, double angle, ID id, Color color, boolean friendly) {
    super(id, color, friendly);
    this.x = x;
    this.y = y;
    w = 12;
    h = 12;
    if(friendly && Player.perks[0][0])
      health = 2;
    target(x, y, tX, tY, angle);
  }

  public void tick() {
    x += velX;
    y += velY;
    collision();
    removeGameObject(false);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int) x, (int) y, w, h);
  }
  /**
   * Sets the x & y velocities of the bullet
   * to x and y vector components
   */
  public void target(int x, int y, int tX, int tY, double angle) {
    double yD = Math.tan(angle);
    double xD  = 1;
    if(tX < x){
      xD = -xD;
      yD = -yD;
    }
    if(Math.abs(tX - x) <= 40){
      if(tY - y > 0){
        yD = Math.abs(yD);
      } else {
        yD = - Math.abs(yD);
      }
    }
    double magnitude = Math.sqrt(xD * xD + yD * yD);
    double speedMultiple = Game.challengeVar + 5;
    if(friendly) {
      if (Player.perks[0][0]) {
        speedMultiple = 14;
      } else {
        speedMultiple = 7;
      }
    }
    velY = ((speedMultiple * yD) / magnitude);
    velX = ((speedMultiple * xD) / magnitude);
  }
}
