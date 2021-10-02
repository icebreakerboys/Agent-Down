package GameObjects;

import java.awt.*;

import GameObjects.Items.PowerUp;
import Main.*;

import java.util.Random;
import GameObjects.Enemies.*;
import GameObjects.Player.*;
import Main.Window;

public abstract class GameObject extends Canvas {

  protected Random r = new Random();
  protected double x, y, velX, velY;
  protected int w, h;
  protected int speed, health = 1;
  protected ID id;
  protected Color color;
  protected boolean stunned = false;
  protected boolean friendly;
  protected BossEnemy boss;
  protected Effect effect = new Effect(20, false);
  protected boolean justHit = false;

  public GameObject(ID id, Color color, boolean friendly) {
    this.id = id;
    this.color = color;
    this.friendly = friendly;
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
      height = Window.HEIGHT;
    }
    if (x <= -w || x >= Window.WIDTH + w || y <= -h || y >= Window.HEIGHT + height)
      Game.handler.removeObject(this);
  }
  /**
   * Handles GameObjects collision with Friendly Bullets
   */
  protected void collision() {
    justHit = effect.coolDown();
    for (int i = 0; i < Game.handler.objects.size(); i++) {
      GameObject tempObject = Game.handler.objects.get(i);
      if (tempObject.getId() == ID.Bullet && tempObject.getFriendly()) {
        if (id == ID.BossEnemy && new Rectangle((int) x, (int) y+48, w, 24).intersects(tempObject.getBounds())) {
            Game.handler.removeObject(tempObject);
            if (Player.perks[2][0])
              stun();
        } else if(((id == ID.Bullet && !friendly) || (id == ID.ShockBullet && !friendly)
                && getBounds().intersects(tempObject.getBounds()))) {
          Game.handler.removeObject(this);
        } else if(getBounds().intersects(tempObject.getBounds()) && !friendly && id != ID.BossEnemy) {
            if(!justHit){
              health--;
              takeDamage(tempObject);
              effect.setEffect(true);
            }
            if(health <= 0) {
              Game.handler.removeObject(this);
              HUD.setScore(50 + (Game.challengeVar * 10));
              if(id == ID.BossWeakPoint){
                boss.killWeakPoint(boss);
              } else if(Player.perks[1][1]){
                spawnPowerUps(tempObject);
              }
            } else if(Player.perks[2][0] && id == ID.BossWeakPoint){
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
  public void sizeNPosVar(boolean size){
    if(size){
      int rInt = r.nextInt(3) + 1;
      w = 16*rInt;
      h = 16*rInt;
    } else {
      w = 16;
      h = 16;
    }
    if(id == ID.ShockerEnemy){
      x = r.nextInt(2) * 576;
    } else {
      x = r.nextDouble() * (528/w) * w + 48;
      y = r.nextInt(Window.HEIGHT) + Window.HEIGHT;
    }
  }

  private void spawnPowerUps(GameObject object){
    GameObject powerUp;
    double randVar = r.nextDouble() * 100;
    double threshold = 100.0/3;
    if(randVar >= 85) {
      randVar = r.nextDouble() * 100;
      if (!Player.getDamageBoost() && randVar >= 2 * threshold) {
        powerUp = new PowerUp(ID.DamageBoost, Color.pink);
      } else if (randVar >= threshold) {
        powerUp = new PowerUp(ID.Magazine, Color.blue);
      } else {
        powerUp = new PowerUp(ID.HealthPack, Color.green);
      }
      powerUp.setX(object.getX());
      powerUp.setY(object.getY());
      Game.handler.addObject(powerUp);
    }
  }
  private void takeDamage(GameObject object){
    object.health--;
    if(object.health <= 0)
      Game.handler.removeObject(object);
  }
  public void stun(){
    stunned = true;
  }
  /**
   * Getters and Setters
   */
  public int getX() {
    return (int) x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return (int) y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public double getVelY() {
    return velY;
  }
  public void setVelY(double VelY){
    velY = VelY;
  }
  public double getVelX(){
    return velX;
  }
  public void setVelX(double VelX){
    velX = VelX;
  }
  public int getW(){
    return w;
  }
  public int getH(){
    return h;
  }
  public ID getId() {
    return id;
  }
  public void setId(ID id) {
    this.id = id;
  }
  public boolean getStunned(){
    return stunned;
  }
  public boolean getFriendly(){
    return friendly;
  }
}
