
import java.awt.*;
import java.util.Random;

public abstract class GameObject extends Canvas {
  protected Random r = new Random();
  //variables
  protected double x, y;
  protected double velX, velY;
  protected int speed;
  protected ID id;
  protected int w, h;
  protected Color color;
  protected Image image;
  protected boolean markedForDelete = false;
  protected int health = 1;
  //constructor
  public GameObject(double x, double y, ID id, int w, int h, Color color) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.w = w;
    this.h = h;
    this.color = color;
    this.health = health;

  }

  //tick
  public abstract void tick();

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    //g.drawImage(image, x, y, 90, 60, this);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, w, h);
  }

  public void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.BulletFriendly && getBounds().intersects(tempObject.getBounds())) {
        takeDamage(1);
        HUD.score += 100;
        HUD.points += 100;
      }
    }
  }

  public void takeDamage(int damage){
    health -= damage;
    if(health <= 0) {
      Game.handler.removeObject(this);
      BossEnemy.numWeakPoints--;
    }
  }

  public void removeEnemy() {
    if (y <= -h) {
      Game.handler.removeObject(this);
    }
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
}
