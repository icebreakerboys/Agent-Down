
import javax.swing.*;
import java.awt.*;

/**
 * Player class
 */
public class Player extends Canvas {
  public static int X, Y;
  public static int velX = 0, velY = 0;
  private static final int w = 48, h = 48;
  private static final Color color = Color.gray;
  private static boolean justHit = false;
  public static boolean hasShotgun = false;
  public static boolean speedBuff = false;
  public static boolean healthBuff = false;
  public static boolean resistanceBuff = false;
  private static int counter = 0;
  public static int AMMO = 0;
  public static int HEALTH;

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
    X += velX;
    Y += velY;
    //x bounds for window
    X = Game.clamp(X, 0, Window.WIDTH - 45);
    //y bounds for window
    Y = Game.clamp(Y, 0, 3 * (Window.HEIGHT / 4) - 45);
    collision();
    if (justHit) {
      counter++;
      if (counter % 50 == 0) {
        counter = 0;
        justHit = false;
      }
    }
    //HEALTH = 100;
    //AMMO = 10;
  }

  public Rectangle getBounds() {
    return new Rectangle(X, Y, w, h);
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

  /**
   * Handles player collision with
   * enemies
   */
  private static void collision() {
    for (int i = 0; i < Game.handler.object.size(); i++) {
      GameObject tempObject = Game.handler.object.get(i);
      //enemy collision
      if (tempObject.getId() == ID.Enemy && Game.player.getBounds().intersects(tempObject.getBounds()) && !justHit) {
        HEALTH -= 10;
        justHit = true;
      }
      //bullet collision
      if (tempObject.getId() == ID.BulletEnemy && Game.player.getBounds().intersects(tempObject.getBounds()) && !justHit) {
        HEALTH -= 10;
        Game.handler.removeObject(tempObject);
        justHit = true;
      }
      //gunItem collision
      if (tempObject.getId() == ID.Magazine && Game.player.getBounds().intersects(tempObject.getBounds())) {
        AMMO += 10;
        Game.handler.removeObject(tempObject);
      }
      if (tempObject.getId() == ID.HealthPack && Game.player.getBounds().intersects(tempObject.getBounds())) {
        if (HEALTH < 100) {
          HEALTH += 30;
          Game.handler.removeObject(tempObject);
        }
      }
    }
  }

  public static void shoot(int x, int y) {
    if(!hasShotgun) {
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x, y, ID.BulletFriendly));
    } else if((x < X && y < Y) || (x > X && y > Y)){
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 25, y + 25, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 12, y + 12, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 12, y - 12, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 25, y - 25, ID.BulletFriendly));
    } else if(Math.abs(x - X) > 50 && y > Y){
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x + 25, y, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x + 12, y, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 12, y, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 25, y, ID.BulletFriendly));
    } else {
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x + 25, y + 25, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x + 12, y + 12, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 12, y - 12, ID.BulletFriendly));
      Game.handler.addObject(new Bullet(X + 24, Y + 24, x - 25, y - 25, ID.BulletFriendly));
    }
  }
}
