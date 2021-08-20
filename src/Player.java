
import javax.swing.*;
import java.awt.*;

/**
 * Player class
 */
public class Player extends Canvas {

  public static int X, Y;
  public static double velX = 0, velY = 0;
  private static final int w = 48, h = 48;
  private static final Color color = Color.gray;
  private static boolean justHit = false, justOnParachute = false;
  public static boolean hasShotgun = false, hasSpeedBuff = false, hasHealthBuff = false, hasResistanceBuff = false;
  private static int counter = 0, counter2 = 0;
  private static double timeDown = 0;
  public static int AMMO = 0;
  public static int HEALTH;
  private static int timeOnParachute = 60;
  private static GameObject parachute = null;
  private static double minVelY = -100;

  //constructor
  public Player(int x, int y) {
    X = x;
    Y = y;
    HEALTH = 100;
  }

  /**
   * Tick class controls usr movement
   * and keeps player within window bounds
   */
  public static void tick() {
    X += (int) velX;
    Y += (int) velY;


    if (justHit) {
      counter++;
      if (counter % 30 == 0) {
        counter = 0;
        justHit = false;
      }
    }

    if(KeyInput.keysDown[2] || KeyInput.keysDown[3]){
      determineVelY();
    } else {
      velY = 0;
      timeDown = 1;
    }

    if(justOnParachute){
      counter2++;
      if(counter2 % 90 == 0){
        counter2 = 0;
        justOnParachute = false;
        parachute = null;
      }
      if(counter2 < 60 && parachute != null && X >= parachute.getX() - 36 && X <= parachute.getX() + 36){
        Y += parachute.getVelY();
        timeDown = 2;
      }
    }

    collision();

    X = (int) Game.clamp(X, 0, Window.WIDTH - 45);
    Y = (int) Game.clamp(Y, 0, 3 * (Window.HEIGHT / 4) - 45);
    velY = Game.clamp(velY, minVelY, 10);
    minVelY = -5;

    HEALTH = 100;
    //AMMO = 10;
  }

  private static void determineVelY() {
    if(KeyInput.keysDown[2] && KeyInput.keysDown[3]) {
      velY = 0;
      timeDown = 1;
    }
    if(KeyInput.keysDown[2]) {
      velY = timeDown;
      if (timeDown <= 8) {
        timeDown += .2;
      }
    } else {
      timeDown = 1;
    }
  }

  /**
   * Render controls the visual components of the player
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    Image image = new ImageIcon(getClass().getClassLoader().getResource("images/Player.png")).getImage();
    if (justHit) {
      if (counter % 5 != 0) {
        //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
        g.setColor(color);
        g.fillRect(X, Y, w, h);
      }
    } else {
      //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
      g.setColor(color);
      g.fillRect(X, Y, w, h);
    }
  }

  public Rectangle getBounds() {
    return new Rectangle(X, Y, w, h);
  }

  /**
   * Handles player collision with
   * enemies
   */
  private static void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      if (tempObject.getId() == ID.Enemy && Game.player.getBounds().intersects(tempObject.getBounds()) && !justHit) {
        HEALTH -= 10;
        justHit = true;
      }
      if (tempObject.getId() == ID.BulletEnemy && Game.player.getBounds().intersects(tempObject.getBounds()) && !justHit) {
        HEALTH -= 10;
        Game.handler.removeObject(tempObject);
        justHit = true;
      }
      if (tempObject.getId() == ID.Magazine && Game.player.getBounds().intersects(tempObject.getBounds())) {
        AMMO += 10;
        Game.handler.removeObject(tempObject);
      }
      if (tempObject.getId() == ID.HealthPack && Game.player.getBounds().intersects(tempObject.getBounds())) {
        if (HEALTH < 100) {
          if (hasHealthBuff) {
            HEALTH += 60;
          } else {
            HEALTH += 30;
          }
          Game.handler.removeObject(tempObject);
        }
      }
      if (tempObject.getId() == ID.BossEnemy && Game.player.getBounds().intersects(tempObject.getBounds())) {
        HEALTH = 0;
      }
      if (tempObject.getId() == ID.Parachute && Game.player.getBounds().intersects(tempObject.getBounds()) && !justOnParachute) {
        timeOnParachute = 1;
        parachute = tempObject;
        justOnParachute = true;
      }
      if (tempObject.getId() == ID.Parachute && Game.player.getBounds2(0, 0, 48, 12).intersects(tempObject.getBounds2(0, +25, 48, 10))) {
        minVelY = -2;
      }
    }
  }

  private Rectangle getBounds2(int x1, int y1, int w1, int h1) {
    return new Rectangle(X + x1, Y + y1, w1, h1);
  }

  public static void shoot(int x, int y, int tX, int tY) {
    int xC = tX - x;
    int yC = tY - y;
    double angle = Math.atan2(yC,xC);
    if(!hasShotgun) {
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, ID.BulletFriendly));
    } else {
      double angle1 = angle + (3.14/36);
      double angle2 = angle - (3.14/36);
      double angle3 = angle + (3.14/72);
      double angle4 = angle - (3.14/72);
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle1, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle2, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle3, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle4, ID.BulletFriendly));
    }
  }
}
