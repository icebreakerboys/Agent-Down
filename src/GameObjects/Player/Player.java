package GameObjects.Player;

import GameObjects.*;
import Main.*;
import Main.MenusAndInputs.*;
import Main.MenusAndInputs.Menu;
import java.awt.*;

//FIXME Try to integrate this into GameObjects.GameObject to Save File Space
// not completely necessary but something to look into
public class Player extends Canvas {

  public static int X, Y;
  public static double velX = 0, velY = 0;
  private static double minVelY = -100;
  private static int ammo = 0;
  private static int health = 100;
  private static final int w = 48, h = 48;
  private static final Color color = Color.gray;
  public static boolean hasShotgun = false, hasFasterBullets = false, hasElectricBoogie = false, hasShockerHacker = false,
          hasBetterPowerUps = false, hasSuperPowerUps = false, hasSuperDuperPowerUps = false, hasSpeedBuff = false;
  private static int perkPoints = 0, perksBought = 0;
  private static boolean justHit = false, justOnParachute = false, stunned = false;
  private static final Effect effect1 = new Effect(30, false), effect2 = new Effect(30, false),
          effect3 = new Effect(30000, false), effect4 = new Effect(90, true);
  private static double timeDown = 0;
  private static GameObject parachute = null;

  public Player(int x, int y) {
    X = x;
    Y = y;
    health = 100;
  }
  /**
   * Handles players movement
   * Keeps player within window bounds
   * Determines if the player hits anything
   * Ends the game when necessary
   */
  public static void tick() {
    if(!stunned) {
      X += (int) velX;
      Y += (int) velY;
      if(hasSpeedBuff){
        X += (int) velX;
        Y += (int) velY;
      }
    }

    justHit = effect1.coolDown();
    stunned = effect2.coolDown();
    hasSpeedBuff = effect3.coolDown();

    justOnParachute = effect4.coolDown();
    parachute = effect4.getGameObject();
    if(effect4.getCounter() < 60 && parachute != null && X >= parachute.getX() - 36 && X <= parachute.getX() + 36){
      Y += parachute.getVelY();
      timeDown = 2;
    }

    if(KeyInput.keysDown[2] || KeyInput.keysDown[3]){
      determineVelY();
    } else {
      velY = 0;
      timeDown = 1;
    }

    collision();
    X = (int) Game.clamp(X, 48, WIDTH - 110);
    Y = (int) Game.clamp(Y, 0, 3 * (HEIGHT / 4) - 45);
    velY = Game.clamp(velY, minVelY, 10);
    minVelY = -5;
    //HEALTH = 100;
    //AMMO = 10;
    //perkPoints = 10;
    health = (int) Game.clamp(health, 0, 100);
    ammo = (int) Game.clamp(ammo, 0, 420);

    if (health == 0) {
      die();
    }
  }
  /**
   * Handles the visual components of the player
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    //Image image = new ImageIcon(getClass().getClassLoader().getResource("images/GameObjects.PlayerAndMore.Player.png")).getImage();
    if (justHit) {
      if (effect1.getCounter() % 5 != 0) {
        //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
        g.setColor(color);
        g.fillRect(X, Y, w, h);
      }
    } else {
      //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
      g.setColor(color);
      g.fillRect(X, Y, w, h);
    }
    if(Menu.menuState == Menu.MSTATE.EndMenu) {
      g.setColor(color);
      g.fillRect(X, Y, w, h);
    }
  }
  /**
   * Handles player collision with GameObjects
   */
  private static void collision() {
    for (int i = 0; i < Game.handler.objects.size(); i++) {
      GameObject tempObject = Game.handler.objects.get(i);
      ID tempID = tempObject.getId();
      if(Game.player.getBounds().intersects(tempObject.getBounds())){
        switch(tempID){
          case Enemy:
            takeDamage(9+ Game.challengeVar);
            break;
          case EnemyBullet:
            takeDamage(9+ Game.challengeVar);
            Game.handler.removeObject(tempObject);
            break;
          case EnemyShockBullet:
            if(!justHit){
              effect2.setEffect(true);
              Game.handler.removeObject(tempObject);
            }
          case BossEnemy:
            takeDamage(100);
            break;
          case FriendlyShockBullet:
            effect3.setEffect(true);
            //counter4 = 0;
            Game.handler.removeObject(tempObject);
            break;
          case Magazine:
            if(hasBetterPowerUps)
              ammo += 12;
            else
              ammo += 8;
            Game.handler.removeObject(tempObject);
            break;
          case HealthPack:
            if (health < 100) {
              if (hasBetterPowerUps)
                health += 50;
              else
                health += 33;
              Game.handler.removeObject(tempObject);
            }
            break;
          case Parachute:
            if(!justOnParachute) {
              effect4.setGameObject(tempObject);
              effect4.setEffect(true);
            }
        }
      }
      if (tempID ==  ID.Parachute && Game.player.getBounds2(0, 0, 48, 12).intersects(tempObject.getBounds2(0, +25, 48, 10))) {
        minVelY = tempObject.getVelY();
      }
    }
  }
  /**
   * Handles player Velocity Y
   */
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
   * Shoots Friendly Bullets at angles
   */
  public static void shoot(int x, int y, int tX, int tY) {
    ammo--;
    int xC = tX - x;
    int yC = tY - y;
    double angle = Math.atan2(yC,xC);
    if(!hasShotgun) {
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, (int) velY, ID.FriendlyBullet, color));
    } else {
      double angle1 = angle + (3.14/9);
      double angle2 = angle - (3.14/9);
      double angle3 = angle + (3.14/18);
      double angle4 = angle - (3.14/18);
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, (int) velY, ID.FriendlyBullet, color));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle1, (int) velY, ID.FriendlyBullet, color));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle2, (int) velY, ID.FriendlyBullet, color));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle3, (int) velY, ID.FriendlyBullet, color));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle4, (int) velY, ID.FriendlyBullet, color));
    }
  }
  /**
   * Returns the Hit box of the GameObjects.PlayerAndMore.Player
   */
  public Rectangle getBounds() {
    return new Rectangle(X, Y, w, h);
  }
  /**
   * Returns a different Hit box of the GameObjects.PlayerAndMore.Player
   * Used for parachute collision
   *
   * @param x1 added to the integer X
   * @param y1 added to the integer Y
   * @param w1 replaces w
   * @param h1 replaces h
   */
  private Rectangle getBounds2(int x1, int y1, int w1, int h1) {
    return new Rectangle(X + x1, Y + y1, w1, h1);
  }

  private static void takeDamage(int damage){
    if(!justHit) {
      health -= damage;
      effect1.setEffect(true);
    }
  }

  public static void resetPerkPoints() {
    perkPoints += perksBought - 1;
    perksBought = 0;
  }

  private static void die() {
    Game.state = Game.STATE.MainMenu; Game.isPlaying = false;
    Menu.menuState = Menu.MSTATE.EndMenu; Menu.restarted = true; Menu.y = 1480;
    health = 100; ammo = 0;
    removePerks(); removeEffects();
    perkPoints = 0; perksBought = 0;
  }

  private static void removeEffects(){
    effect1.setEffect(false); effect2.setEffect(false);
    effect3.setEffect(false); effect4.setEffect(false);
    effect1.setCounter(0); effect2.setCounter(0);
    effect3.setCounter(0); effect4.setCounter(0);
    effect4.setGameObject(null);
    justHit = false; stunned = false;
    hasSpeedBuff = false; justOnParachute = false;
    parachute = null;
  }

  public static void removePerks() {
    hasBetterPowerUps = false; hasSuperPowerUps = false; hasSuperDuperPowerUps = false;
    hasFasterBullets = false; hasShotgun = false;
    hasShockerHacker = false; hasElectricBoogie = false;
  }

  public static void buyMidPerk() {
    if(!hasBetterPowerUps && perkPoints >= 1){
      hasBetterPowerUps = true;
      perkPoints--;
      perksBought++;
    } else if(!hasSuperPowerUps && perkPoints >= 2 && perksBought <= 6){
      hasSuperPowerUps = true;
      perkPoints -= 2;
      perksBought += 2;
    } else if(!hasSuperDuperPowerUps && perkPoints >= 3 && perksBought <= 5){
      hasSuperDuperPowerUps = true;
      perkPoints -= 3;
      perksBought += 3;
    }
  }

  public static void buyBotPerk() {
    if(!hasShockerHacker && perkPoints >= 2 && perksBought <= 6){
      hasShockerHacker = true;
      perkPoints -= 2;
      perksBought += 2;
    } else if(!hasElectricBoogie && perkPoints >= 3 && perksBought <= 5) {
      hasElectricBoogie = true;
      perkPoints -= 3;
      perksBought += 3;
    }
  }

  public static void buyTopPerk() {
    if(!hasFasterBullets && perkPoints >= 2 && perksBought <= 6){
      hasFasterBullets = true;
      perkPoints -= 2;
      perksBought += 2;
    } else if(!hasShotgun && perkPoints >= 3 && perksBought <= 5) {
      hasShotgun = true;
      perkPoints -= 3;
      perksBought += 3;
    }
  }

  public static int getPerkPoints() {
    return perkPoints;
  }

  public static int getHealth() {
    return health;
  }

  public static int getAmmo(){
    return ammo;
  }
}
