package GameObjects;

import java.awt.*;
import Main.*;
import java.util.Random;
import GameObjects.Enemies.*;
import GameObjects.Player.*;

public abstract class GameObject extends Canvas {

  protected Random r = new Random();
  protected double x, y, velX, velY;
  protected int w, h;
  protected int speed, health = 1;
  protected ID id;
  protected Color color;
  protected boolean stunned = false;
  protected BossEnemy boss;

  public GameObject(double x, double y, ID id, int w, int h, Color color) {
    this.id = id;
    this.color = color;
    this.w = w;
    this.h = h;
    this.x = x;
    this.y = y;
  }

  /**
   * Handles GameObjects.GameObject movement & collision
   */
  public abstract void tick();

  /**
   * Handles the visual components of GameObjects
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    //g.drawImage(image, x, y, 90, 60, this);
  }
  /**
   * Determines if the GameObjects.GameObject needs to be deleted & then deletes it
   *
   * @param movesUp Determines if it needs to be deleted while below the screen
   */
  public void removeGameObject(boolean movesUp) {
    int height = h;
    if(movesUp){
      height = HEIGHT;
    }
    if (x <= -w || x >= WIDTH + w || y <= -h || y >= HEIGHT + height)
      Game.handler.removeObject(this);
  }
  /**
   * Handles GameObjects collision with Friendly Bullets
   */
  public void collision() {
    for (int i = 0; i < Game.handler.objects.size(); i++) {
      GameObject tempObject = Game.handler.objects.get(i);
      if (tempObject.getId() == ID.FriendlyBullet){
        if (getId() == ID.BossEnemy) {
          if (getBounds2(0, h, w, h).intersects(tempObject.getBounds())) {
            Game.handler.removeObject(tempObject);
            if (Player.hasShockerHacker)
              stun();
          }
        } else if(getBounds().intersects(tempObject.getBounds())) {
          health--;
          Game.handler.removeObject(tempObject);
          if(health <= 0) {
            Game.handler.removeObject(this);
            HUD.setScore(50 + (Game.challengeVar * 10));

            if(getId() == ID.BossWeakPoint){
              boss.killWeakPoint(boss);
            }
          } else if(Player.hasShockerHacker && getId() == ID.BossWeakPoint){
            boss.stun();
          }
        }
      }
    }
  }
  /**
   * Returns the Hit box of GameObjects
   */
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, w, h);
  }
  /**
   * Returns a different Hit box of GameObjects
   * Used for GameObjects.Enemies.BossEnemy collision
   *
   * @param x1 added to the double x
   * @param y1 added to the double Y
   * @param w1 replaces w
   * @param h1 replaces h
   */
  public Rectangle getBounds2(int x1, int y1, int w1, int h1) {
    return new Rectangle((int) x + x1, (int) y + y1, w1, h1);
  }
  /**
   * Getters and Setters
   */
  public void stun(){
    stunned = true;
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

  public boolean getstunned(){
    return stunned;
  }

}
