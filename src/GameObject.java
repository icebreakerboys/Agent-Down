
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

  public Rectangle getBounds2(int x1, int y1, int w1, int h1) {
    return new Rectangle((int) x + x1, (int) y + y1, w1, h1);
  }

  public void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.BulletFriendly && getBounds().intersects(tempObject.getBounds())) {
        health--;
        if(health <= 0) {
          Game.handler.removeObject(this);
        }
        HUD.score += 100;
        HUD.points += 100;
        Game.handler.removeObject(tempObject);
      }
    }
  }

  public void removeGameObject(boolean movesUp) {
    int height = h;
    if(movesUp){
      height = Window.HEIGHT;
    }
    if (x <= -w || x >= Window.WIDTH + w || y <= -h || y >= Window.HEIGHT + height)
      Game.handler.removeObject(this);
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
