
import java.awt.*;

public class Bullet extends GameObject {

  public Bullet(int x, int y, int tX, int tY, double angle, int velY, ID id) {
    super(x, y, id, 12, 12, Color.black);
    if(id != ID.ShockBulletEnemy) {
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

  public void target(int x, int y, int tX, int tY, double angle) {
    double yD = Math.tan(angle);
    double xD  = 1;
    if(tX < x){
      xD = -1;
      yD = -yD;
    }
    if(Math.abs(tX - x) <= 10){
      if(tY - y > 0){
        yD = Math.abs(yD);
      } else {
        yD = - Math.abs(yD);
      }
    }
    velY = ((7 * yD) / (Math.sqrt(xD * xD + yD * yD)));
    velX = ((7 * xD) / (Math.sqrt(xD * xD + yD * yD)));
  }

  @Override
  public void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.BulletFriendly && id == ID.BulletEnemy && getBounds().intersects(tempObject.getBounds()))
        Game.handler.removeObject(this);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, w, h);
  }
}
