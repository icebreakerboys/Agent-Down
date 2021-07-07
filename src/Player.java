
import javax.swing.*;
import java.awt.*;
import java.beans.ConstructorProperties;

/**
 * Player class
 */
public class Player extends Canvas {
  public static int X, Y;
  public static int velX = 0, velY = 0;
  private static Color color;
  private static int w, h;
  private static Boolean justHit = false;
  private static int counter = 0;
  public static int AMMO = 0;
  public static int HEALTH;

  //constructor
  public Player(int x, int y, int w, int h, Color color) {
    X = x;
    Y = y;
    Player.w = w;
    Player.h = h;
    Player.color = color;
    HEALTH = 100;
    Game.handler.addObject(new JetStreams());
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
      if (counter % 5 != 0)
        g.drawImage(image, X - 8, Y - 4, 69, 56, this);
    } else {
      g.drawImage(image, X - 8, Y - 4, 69, 56, this);
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
        HEALTH += 30;
        Game.handler.removeObject(tempObject);
      }
    }
  }

  public static void shoot(int x, int y) {
    Game.handler.addObject(new Bullet(Player.X + 24, Player.Y + 24, x, y, ID.BulletFriendly));
  }
}
