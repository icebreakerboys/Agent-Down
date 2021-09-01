package GameObjects;

import Main.*;
import GameObjects.Player.Player;

import java.awt.*;

public class Bullet extends GameObject {

  public Bullet(int x, int y, int tX, int tY, double angle, int velY, ID id, Color color) {
    super(x, y, id, 12, 12, color);
    if(id != ID.EnemyShockBullet && id != ID.FriendlyShockBullet) {
      target(x, y, tX, tY, angle);
    } else {
      this.velY = velY;
      this.velX = Game.challengeVar + 7;
      if(x == 600)
        this.velX = -this.velX;
    }
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
      xD = -1;
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
    if(id == ID.FriendlyBullet){
      if(Player.hasFasterBullets) {
        velY = ((14 * yD) / magnitude);
        velX = ((14 * xD) / magnitude);
      } else {
        velY = ((7 * yD) / magnitude);
        velX = ((7 * xD) / magnitude);
      }
    } else {
      velY = (((Game.challengeVar + 5) * yD) / magnitude);
      velX = (((Game.challengeVar + 5) * xD) / magnitude);
    }
  }

  @Override
  public void collision() {
    for (int i = 0; i < Game.handler.objects.size(); i++) {
      GameObject tempObject = Game.handler.objects.get(i);
      if (tempObject.getId() == ID.FriendlyBullet && id == ID.EnemyBullet && getBounds().intersects(tempObject.getBounds()))
        Game.handler.removeObject(this);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, w, h);
  }
}
