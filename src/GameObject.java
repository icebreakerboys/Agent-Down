
import java.awt.*;
import java.util.Random;

public abstract class GameObject extends Canvas {
  protected Random r = new Random();
  //variables
  protected int x, y;
  protected int velX, velY;
  protected int speed;
  protected ID id;
  protected int w, h;
  protected Color color;
  protected Image image;
  protected boolean markedForDelete = false;

  //constructor
  public GameObject(int x, int y, ID id, int w, int h, Color color) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  //tick
  public abstract void tick();

  public void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.BulletFriendly && getBounds().intersects(tempObject.getBounds())) {
        Game.handler.removeObject(this);
      }
    }
  }

  public void markForDelete() {
    markedForDelete = true;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, w, h);
  }

  public void resetPositionAndSpeed(int h, int speed) {
    if (y <= -h) {
      y = Window.HEIGHT;
      x = r.nextInt(Window.WIDTH - w);
      velY = -(r.nextInt(speed)) - 1;
      if (markedForDelete) {
        Game.handler.removeObject(this);
      }
    }
  }

  public void removeGameObject() {
    if (x <= -w || x >= Window.WIDTH + w || y <= -h || y >= Window.HEIGHT + h)
      Game.handler.removeObject(this);
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, w, h);
    //g.drawImage(image, x, y, 90, 60, this);
  }

  //getters and setters
  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setId(ID id) {
    this.id = id;
  }

  public ID getId() {
    return id;
  }

  public void setVelX(int velX) {
    this.velX = velX;
  }

  public void setVelY(int velY) {
    this.velY = velY;
  }

  public int getVelX() {
    return velX;
  }

  public int getVelY() {
    return velY;
  }
}
