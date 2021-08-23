
import java.awt.*;
import java.util.Random;

public abstract class GameObject extends Canvas {

  protected Random r = new Random();
  protected double x, y, velX, velY;
  protected int w, h;
  protected int speed, health = 1;
  protected ID id;
  protected Color color;
  protected Image image;
  protected boolean stunned = false;
  protected BossEnemy boss;

  //constructor
  public GameObject(double x, double y, ID id, int w, int h, Color color) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  //tick
  public abstract void tick();

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    //g.drawImage(image, x, y, 90, 60, this);
  }

  public void removeGameObject(boolean movesUp) {
    int height = h;
    if(movesUp){
      height = Window.HEIGHT;
    }
    if (x <= -w || x >= Window.WIDTH + w || y <= -h || y >= Window.HEIGHT + height)
      Game.handler.removeObject(this);
  }

  public void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.BulletFriendly){
        if (getId() == ID.BossEnemy) {
          if (getBounds2(0, h, w, h).intersects(tempObject.getBounds())) {
            Game.handler.removeObject(tempObject);
            if (Player.hasBetterBullets)
              stun();
          }
        } else if(getBounds().intersects(tempObject.getBounds())) {
          health--;
          Game.handler.removeObject(tempObject);
          if(health <= 0) {
            Game.handler.removeObject(this);
            HUD.score += 100;
            HUD.points += 100;
            if(getId() == ID.BossWeakPoint){
              boss.killWeakPoint(boss);
            }
          } else if(Player.hasBetterBullets && getId() == ID.BossWeakPoint){
            boss.stun();
          }
        }
      }
    }
  }

  public void stun(){
    stunned = true;
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, w, h);
  }

  public Rectangle getBounds2(int x1, int y1, int w1, int h1) {
    return new Rectangle((int) x + x1, (int) y + y1, w1, h1);
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return (int) x;
  }

  public int getY() {
    return (int) y;
  }

  public void setId(ID id) {
    this.id = id;
  }

  public ID getId() {
    return id;
  }

  public double getVelY() {
    return velY;
  }
}
